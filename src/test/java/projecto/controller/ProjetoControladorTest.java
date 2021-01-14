package projecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.model.dto.ProjetoNewDTO;
import projecto.repositories.*;
import projecto.service.ProjectServiceImpl;
import projecto.service.TarefaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjetoControlador.class)
public class ProjetoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectServiceImpl projectService;

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
        Projeto projeto=new Projeto();
        String projectoAsJsonString=new ObjectMapper().writeValueAsString(projeto);

        when(projectService.findById((int) 1L)).thenReturn(Optional.of(projeto));

        String httpResponseAsString=mockMvc.perform(get("/projeto/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/projeto/2")).andExpect(status().isNotFound());
    }
    @Test
    void findAll() throws Exception {
        Projeto projeto1=new Projeto();
        Projeto projeto=new Projeto();
        Projeto projeto2=new Projeto();

        List<Projeto> projecto = Arrays.asList(projeto,projeto1,projeto2);

        when(projectService.findAll()).thenReturn(projecto);

        String httpResponseAsString=mockMvc.perform(get("/projeto")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }
    @Test
    void criaProjecto() throws Exception {
        ProjetoNewDTO projeto1=new ProjetoNewDTO();
        Projeto projeto3=new Projeto();
        projeto1.setDescricao("teste");
        when(this.projectService.createProjecto(projeto1)).thenReturn(Optional.of(projeto3));

        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(projeto1);

        mockMvc.perform(post("/projeto").content(explicadorAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
    @Test
    void deleteProjeto() throws Exception {
        Projeto projeto=new Projeto();
        ProjetoNewDTO projeto1=new ProjetoNewDTO();
        projeto.setDescricao("novoexplicador@mail.com");

        when(this.projectService.createProjecto(projeto1)).thenReturn(Optional.of(projeto));

        String projString=new ObjectMapper().writeValueAsString(projeto1);

        mockMvc.perform(post("/projeto").content(projString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        String projString2=new ObjectMapper().writeValueAsString(1);

        mockMvc.perform(delete("/projeto/1").content(projString2).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    @Test
    void insereTarefa() throws Exception {
        Projeto projeto=new Projeto();
        projeto.setDescricao("Teste");

        Tarefa tarefa=new Tarefa();


        String tarefaString=objectMapper.writeValueAsString(projeto);

        when(projectService.insereTarefa(tarefa)).thenReturn(Optional.of(projeto));

        mockMvc.perform(
                patch("/projeto")
                        .content(tarefaString)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());


    }

}
