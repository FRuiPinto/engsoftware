package projecto.service;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import projecto.model.*;
import projecto.model.dto.TarefaNewDTO;
import projecto.repositories.ClienteRepository;
import projecto.repositories.ColaboradorRepository;
import projecto.repositories.ProjetoRepository;
import projecto.repositories.TarefaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TarefaServiceImpl.class)
class TarefaServiceImplTest {

    @Autowired
    private TarefaService tarefaService;

    @MockBean
    private TarefaRepository tarefaRepository;

    @MockBean
    private ProjetoRepository projetoRepository;

    @Test
    void findById() {
        when(tarefaRepository.findById((int) 1L)).thenReturn(Optional.of(new Tarefa()));
        assertTrue(tarefaService.findById((int) 1L).isPresent());
        assertTrue(tarefaService.findById((int) 2L).isEmpty());

    }

    @Test
    void findAll() {
        when(tarefaRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(tarefaService.findAll());
    }

    @Test
    void createTarefa() {

        Tarefa nova = new Tarefa();
        nova.setDescricao("Teste");
        tarefaRepository.save(nova);
        Tarefa nova1 = new Tarefa();
        Projeto proj = new Projeto();
        when(tarefaRepository.findByDescricao("Teste")).thenReturn(Optional.of(nova1));
        when(projetoRepository.save(proj)).thenReturn(proj);

    }

    @Test
    void deleteTarefa() {

        Tarefa nova = new Tarefa();
        nova.setDescricao("Teste");
        tarefaRepository.save(nova);
        Tarefa nova1 = new Tarefa();
        when(tarefaRepository.deleteByDescricao("Teste")).thenReturn(Optional.of(nova1));

    }

    @Test
    void updateTarefaHoras() {

        Tarefa nova = new Tarefa();
        nova.setDescricao("Teste");
        tarefaRepository.save(nova);
        Tarefa nova1 = new Tarefa();
        when(tarefaService.updateTarefaHoras(nova.getId(),10)).thenReturn(Optional.of(nova1));

    }

    @Test
    void trocaColadoradores() {
        Tarefa nova = new Tarefa();
        nova.setDescricao("Teste");
        tarefaRepository.save(nova);
        Tarefa nova1 = new Tarefa();
        when(tarefaService.trocaColadoradores(nova.getId(),1,2)).thenReturn(Optional.of(nova1));
    }


}
