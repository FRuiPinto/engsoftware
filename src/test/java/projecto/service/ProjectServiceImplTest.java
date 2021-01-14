package projecto.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import projecto.model.Cliente;
import projecto.model.Colaborador;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.repositories.ClienteRepository;
import projecto.repositories.ColaboradorRepository;
import projecto.repositories.ProjetoRepository;
import projecto.repositories.TarefaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ProjectServiceImpl.class)
class ProjectServiceImplTest {
    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private TarefaRepository tarefaRepository;

    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private ColaboradorRepository colaboradorRepository;
    @MockBean
    private ClienteRepository clienteRepository;
    @Test
    void findById() {
        when(projetoRepository.findById((int) 1L)).thenReturn(Optional.of(new Projeto()));
        assertTrue(projetoService.findById((int) 1L).isPresent());
        assertTrue(projetoService.findById((int) 2L).isEmpty());
    }

    @Test
    void findAll() {
        when(projetoRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(projetoService.findAll());
    }

    @Test
    void createProjecto() {
        Projeto nova = new Projeto();
        nova.setDescricao("Teste");
        projetoRepository.save(nova);
        when(projetoRepository.findByDescricao("Teste")).thenReturn(Optional.of(nova));
    }

    @Test
    void deleteProjeto() {

        Projeto nova = new Projeto();
        nova.setDescricao("Teste");
        projetoRepository.save(nova);
        when(projetoRepository.deleteByDescricao("Teste")).thenReturn(Optional.of(nova));

    }

    @Test
    void insereTarefa() {

        Colaborador colaborador = new Colaborador("Tiago Correia", 3);
        colaboradorRepository.saveAll(Arrays.asList(colaborador));
        Cliente cliente = new Cliente("António Pinto", "41231");
        clienteRepository.saveAll(Arrays.asList(cliente));
        Projeto projeto = new Projeto("Gestão de RH", cliente, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        projetoRepository.saveAll(Arrays.asList(projeto));

        Tarefa tarefa = new Tarefa(LocalDate.of(2020, 6, 13), LocalDate.of(2020, 9, 20), "Software Test Engineer I", colaborador, projeto, 91);
        tarefaRepository.saveAll(Arrays.asList(tarefa));

        when(projetoService.insereTarefa(tarefa)).thenReturn(Optional.of(projeto));

    }
}