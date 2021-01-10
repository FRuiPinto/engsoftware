package projecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projecto.model.Tarefa;
import projecto.repositories.ColaboradorRepository;
import projecto.repositories.TarefaRepository;
import projecto.service.exception.DataIntegrityException;
import projecto.service.exception.ObjectNotFoundException;
import projecto.model.Colaborador;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {


    private final ColaboradorRepository colaboradorRepository;
    private final TarefaRepository tarefaRepository;

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository,TarefaRepository tarefaRepository){
        this.colaboradorRepository=colaboradorRepository;
        this.tarefaRepository = tarefaRepository;
    }
    public Colaborador find(Integer id) {
        Optional<Colaborador> colab = colaboradorRepository.findById(id);
        return colab.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Colaborador.class.getName()));
    }
    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }
    public Colaborador insert(Colaborador colab) {
        colab.setId(null);
        colab = colaboradorRepository.save(colab);
        return colab;
    }
    public Colaborador update(Colaborador colab) {
        Colaborador newObj = find(colab.getId());
        updateDadosColaborador(newObj, colab);
        return colaboradorRepository.save(newObj);
    }
    public void delete(Integer id) {
        find(id);
        try {
            List<Tarefa> tarefas = tarefaRepository.findAllByColaboradorId(id);
            for(Tarefa t : tarefas){
                t.setColaborador(null);
                tarefaRepository.save(t);
            }
            colaboradorRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }
    private void updateDadosColaborador(Colaborador newObj, Colaborador colab) {
        newObj.setNome(colab.getNome());
        newObj.setFuncao(colab.getFuncao());
    }
}
