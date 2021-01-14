package projecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projecto.model.Cliente;
import projecto.model.Colaborador;
import projecto.repositories.*;
import projecto.service.ClienteService;
import projecto.service.ColaboradorService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ColaboradorControlador.class)
class ColaboradorControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ColaboradorService colaboradorService;

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
        Colaborador colaborador=new Colaborador();
        String colaboradorAsJsonString=new ObjectMapper().writeValueAsString(colaborador);

        when(colaboradorService.find(1)).thenReturn(colaborador);

        String httpResponseAsString=mockMvc.perform(get("/colaborador/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }

    @Test
    void findAll() throws Exception {
        Colaborador colaborador1=new Colaborador();
        Colaborador colaborador2=new Colaborador();
        Colaborador colaborador3=new Colaborador();

        List<Colaborador> colaboradores= Arrays.asList(colaborador1,colaborador2,colaborador3);

        when(colaboradorService.findAll()).thenReturn(colaboradores);

        String httpResponseAsString=mockMvc.perform(get("/colaborador/")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertNotNull(httpResponseAsString);

    }

    @Test
    void insert() throws Exception {
        Colaborador colaborador=new Colaborador();
        colaborador.setNome("Novo Colaborador");
        colaborador.setFuncao(3);

        when(this.colaboradorService.insert(colaborador)).thenReturn(colaborador);

        String clienteAsJsonString=new ObjectMapper().writeValueAsString(colaborador);

        mockMvc.perform(post("/colaborador").content(clienteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }
}