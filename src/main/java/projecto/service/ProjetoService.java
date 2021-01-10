package projecto.service;

import org.springframework.stereotype.Service;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.model.dto.ProjetoNewDTO;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {

    Optional<Projeto> findById(Integer id);
    Optional<Projeto> createProjecto(ProjetoNewDTO projeto);
    List<Projeto> findAll();
    Optional<Projeto> insereTarefa(Tarefa tarefa);
    void deleteProjeto(Integer projeto);

}
