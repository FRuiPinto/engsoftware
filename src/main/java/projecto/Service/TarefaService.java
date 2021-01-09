package projecto.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projecto.Repositories.*;
import projecto.Service.Exception.DataIntegrityException;
import projecto.Service.Exception.ObjectNotFoundException;
import projecto.model.*;
import projecto.model.DTO.ProjetoNewDTO;
import projecto.model.DTO.TarefaNewDTO;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {


    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa find(Integer id) {
        Optional<Tarefa> obj = tarefaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Projeto.class.getName()));
    }

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Tarefa insert(TarefaNewDTO obj) {
        Tarefa t1 = fromDTO(obj);
        t1 = tarefaRepository.save(t1);
        return t1;
    }

    public void delete(Integer id) {
        find(id);
        try {
            tarefaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public Tarefa fromDTO(TarefaNewDTO obj){

        Colaborador clo = colaboradorRepository.getOne(obj.getIdcolaborador());
        Projeto p1 = projetoRepository.getOne(obj.getIdprojeto());
        Tarefa t1 = new Tarefa(obj.getDtini(),obj.getDtfim(),obj.getDescricao(),clo,p1);
        p1.addTarefa(t1);
        clo.addTarefa(t1);
        return t1;
    }

}
