package projecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecto.model.Cliente;
import projecto.model.Colaborador;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.model.dto.ProjetoNewDTO;
import projecto.model.dto.TarefaNewDTO;
import projecto.repositories.ClienteRepository;
import projecto.repositories.ColaboradorRepository;
import projecto.repositories.ProjetoRepository;
import projecto.repositories.TarefaRepository;
import projecto.service.exception.DataIntegrityException;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjetoService {

    private ProjetoRepository projetoRepository;
    private ClienteRepository clienteRepository;
    private ColaboradorRepository colaboradorRepository;
    private TarefaRepository tarefaRepository;

    @Autowired
    public ProjectServiceImpl(ProjetoRepository projetoRepository, ClienteRepository clienteRepository, ColaboradorRepository colaboradorRepository, TarefaRepository tarefaRepository) {
        this.projetoRepository = projetoRepository;
        this.clienteRepository = clienteRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public Optional<Projeto> findById(Integer id) {
        return projetoRepository.findById(id);
    }

    @Override
    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    @Override
    public Optional<Projeto> createProjecto(ProjetoNewDTO projeto) {
        Optional<Projeto> projetoNew = fromDTO(projeto);
        if (projetoNew.isPresent()) {
            projetoNew.get().getCliente().addProjecto(projetoNew.get());
            clienteRepository.save(projetoNew.get().getCliente());
            projetoRepository.save(projetoNew.get());
            return projetoNew;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> insereTarefa(Tarefa tarefa) {
        Optional<Colaborador> colaborador = colaboradorRepository.findById(tarefa.getColaborador().getId());
        Optional<Projeto> projeto = projetoRepository.findById(tarefa.getProjeto().getId());
        if (projeto.isPresent() && colaborador.isPresent()) {
            colaborador.get().addTarefa(tarefa);
            projeto.get().addTarefa(tarefa);
            projetoRepository.save(projeto.get());
            colaboradorRepository.save(colaborador.get());
            return projeto;
        }
        return Optional.empty();
    }

    @Override
    public void deleteProjeto(Integer projetoId) {
        try {
            projetoRepository.deleteById(projetoId);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Existem outros registos em outras entidades");
        }
    }

    public Optional<Projeto> fromDTO(ProjetoNewDTO proj) {
        Optional<Cliente> cli1 = clienteRepository.findById(proj.getIdCliente());
        return cli1.map(cliente -> new Projeto(proj.getDescricao(), cliente, proj.getDtIniPrevisto(), proj.getDtFimPrevisto()));
    }
    public ProjetoNewDTO toDTO(Projeto projeto) {
        return new ProjetoNewDTO(projeto.getCliente().getId(),projeto.getDescricao(),projeto.getDtIniPrevisto(),projeto.getDtFimPrevisto());
    }

}
