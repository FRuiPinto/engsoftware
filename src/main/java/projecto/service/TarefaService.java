package projecto.service;

import projecto.model.Tarefa;
import projecto.model.dto.TarefaNewDTO;

import java.util.List;
import java.util.Optional;

public interface TarefaService {

    Optional<Tarefa> findById(Integer tarefaId);

    List<Tarefa> findAll();

    Optional<Tarefa> createTarefa(TarefaNewDTO projeto);

    void deleteTarefa(Integer idTarefa);

    Optional<Tarefa> updateTarefaHoras(Integer idTarefa,Integer horas);

    Optional<Tarefa> trocaColadoradores(Integer idTarefa,Integer novoColaborador, Integer antigoColaborador);

}
