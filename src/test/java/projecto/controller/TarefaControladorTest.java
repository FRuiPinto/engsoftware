package projecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import projecto.model.Colaborador;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.repositories.*;
import projecto.service.ColaboradorService;
import projecto.service.TarefaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaControlador.class)
class TarefaControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;

    @MockBean
    private TarefaRepository tarefaRepository;

    @MockBean
    private ProjetoRepository projetoRepository;
    @MockBean
    private ColaboradorRepository colaboradorRepository;
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private FuncaoColaboradorRepository funcaoColaboradorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void find() throws Exception {
        Tarefa tarefa=new Tarefa();
        String tarefaAsJsonString=new ObjectMapper().writeValueAsString(tarefa);

        when(tarefaService.findById((int) 1L)).thenReturn(Optional.of(tarefa));

        String httpResponseAsString=mockMvc.perform(get("/tarefa/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/tarefa/2")).andExpect(status().isNotFound());

    }

    @Test
    void findAll() throws Exception {
        Tarefa tarefa1=new Tarefa();
        Tarefa tarefa2=new Tarefa();
        Tarefa tarefa3=new Tarefa();

        List<Tarefa> tarefas= Arrays.asList(tarefa1,tarefa2,tarefa3);

        when(tarefaService.findAll()).thenReturn(tarefas);

        String httpResponseAsString=mockMvc.perform(get("/tarefa/")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }
}
