package projecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class TarefaServiceImpl implements TarefaService {

    private final ProjetoRepository projetoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final TarefaRepository tarefaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public TarefaServiceImpl(ProjetoRepository projetoRepository, ColaboradorRepository colaboradorRepository, TarefaRepository tarefaRepository, ClienteRepository clienteRepository) {
        this.projetoRepository = projetoRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.tarefaRepository = tarefaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Tarefa> findById(Integer tarefaId) {
        return tarefaRepository.findById(tarefaId);
    }

    @Override
    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    @Override
    public Optional<Tarefa> createTarefa(TarefaNewDTO tarefaNewDTO) {
        Optional<Tarefa> tarefaNova = Optional.of(fromDTO(tarefaNewDTO));
        if (tarefaNova.isPresent()) {
            tarefaNova.get().getProjeto().addTarefa(tarefaNova.get());
            projetoRepository.save(tarefaNova.get().getProjeto());
            tarefaRepository.save(tarefaNova.get());
            return tarefaNova;
        }
        return Optional.empty();
    }

    @Override
    public void deleteTarefa(Integer idTarefa) {
        try {
            tarefaRepository.deleteById(idTarefa);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Existem outros registos em outras entidades");
        }
    }

    @Override
    public Optional<Tarefa> updateTarefaHoras(Integer id, Integer horas) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            double percentagem = (double) (horas * 100) / tarefaOptional.get().getHorasPrevistas();
            if (tarefaOptional.get().getTarefaEvolucao().getHorasExecutadas() + horas >= 100) {
                tarefaOptional.get().setAtivo(false);
                tarefaOptional.get().updateHoras(100);
                tarefaOptional.get().getTarefaEvolucao().setHorasExecutadas(tarefaOptional.get().getHorasPrevistas());

            } else {
                tarefaOptional.get().getTarefaEvolucao().setHorasExecutadasTemp(horas);
                tarefaOptional.get().getTarefaEvolucao().setPerceExecutadasTemp(percentagem);

            }
            tarefaRepository.save(tarefaOptional.get());
            return tarefaOptional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Tarefa> trocaColadoradores(Integer idTarefa, Integer novoColaborador, Integer antigoColaborador) {
        Optional<Colaborador> optionalColaboradorNovo = colaboradorRepository.findById(novoColaborador);
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(idTarefa);
        if(tarefaOptional.isPresent() && optionalColaboradorNovo.isPresent()){
            if(tarefaOptional.get().getColaborador() == null){
                // update utilizador
            }
            tarefaOptional.get().setColaborador(optionalColaboradorNovo.get());
            tarefaRepository.save(tarefaOptional.get());
            return tarefaOptional;
        }
        return Optional.empty();
    }

    public Tarefa fromDTO(TarefaNewDTO tarefaNewDTO) {
        Optional<Projeto> projeto = projetoRepository.findById(tarefaNewDTO.getIdprojeto());
        return projeto.map(value -> new Tarefa(tarefaNewDTO.getDtini(), tarefaNewDTO.getDtfim(), tarefaNewDTO.getDescricao(), value, tarefaNewDTO.getHorasPrevistas())).orElse(null);
    }

}

