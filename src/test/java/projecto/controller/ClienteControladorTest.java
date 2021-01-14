package projecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projecto.model.Cliente;
import projecto.model.Projeto;
import projecto.model.dto.ProjetoNewDTO;
import projecto.repositories.ClienteRepository;
import projecto.repositories.ColaboradorRepository;
import projecto.repositories.ProjetoRepository;
import projecto.repositories.TarefaRepository;
import projecto.service.ClienteService;
import projecto.service.ProjectServiceImpl;
import projecto.service.TarefaService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ClienteControlador.class)
class ClienteControladorTest {

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
    private ClienteService clienteService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void find() throws Exception {
        Cliente cliente=new Cliente();
        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(cliente);

        when(clienteService.find((int) 1L)).thenReturn(Optional.of(cliente));

        String httpResponseAsString=mockMvc.perform(get("/cliente/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

        mockMvc.perform(get("/cliente/2")).andExpect(status().isNotFound());

    }

    @Test
    void findAll() throws Exception {

        Cliente cliente1=new Cliente();
        Cliente cliente2=new Cliente();
        Cliente cliente3=new Cliente();

        List<Cliente> clientes= Arrays.asList(cliente1,cliente2,cliente3);

        when(clienteService.findAll()).thenReturn(clientes);

        String httpResponseAsString=mockMvc.perform(get("/cliente")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }


    @Test
    void insert() throws Exception {
        Cliente cliente=new Cliente();
        cliente.setNif("teste");
        when(this.clienteService.insert(cliente)).thenReturn(Optional.of(cliente));

        String explicadorAsJsonString=new ObjectMapper().writeValueAsString(cliente);

        mockMvc.perform(post("/cliente").content(explicadorAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());    }

    @Test
    void update() {




    }

    @Test
    void delete() {

    }

    @Test
    void clientesProjecto() {
    }

    @Test
    void clientesProjectoValor() {
    }

    @Test
    void clientesProjectoTemp() {
    }
}
