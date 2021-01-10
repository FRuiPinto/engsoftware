package projecto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projecto.model.Colaborador;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.model.pattern.TarefaBuilder;
import projecto.repositories.TarefaRepository;
import projecto.service.TarefaService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaControlador.class)
class TarefaControladorTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaRepository tarefaRepository;

    @MockBean
    private TarefaService tarefaService;

    @Test
    void find() throws Exception {

        Tarefa tarefa=new Tarefa(LocalDate.of(2020, 6, 13), LocalDate.of(2020, 9, 20),"new String()",new Projeto(),12);
        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(tarefa);
        tarefaRepository.save(tarefa);

        when(tarefaRepository.findById(1)).thenReturn(Optional.of(tarefa));

        String httpResponseAsString=mockMvc.perform(get("/tarefa/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);
        assertEquals(explicadorAsJsonString,httpResponseAsString);

        mockMvc.perform(get("/tarefa/2")).andExpect(status().isNotFound());
    }
    @Test
    void criaTarefa() throws Exception {
        Tarefa nova = new TarefaBuilder(new Projeto()).setAtivo(true).setColaborador(new Colaborador()).build();
        when(this.tarefaRepository.save(nova)).thenReturn(nova);
        Tarefa nova1 = new TarefaBuilder(new Projeto()).setAtivo(true).setColaborador(new Colaborador()).build();
        nova1.setAtivo(false);
        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(nova);

        mockMvc.perform(post("/tarefa").content(explicadorAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        String explicadorExistenteAsJsonString=new ObjectMapper().writeValueAsString(nova1);
        when(this.tarefaRepository.findById(1)).thenReturn(Optional.of(nova1));

        mockMvc.perform(post("/explicador").content(explicadorExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());    }

}
