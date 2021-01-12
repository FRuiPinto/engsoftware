package projecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projecto.model.Cliente;
import projecto.service.ClienteService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void find() throws Exception {

        Cliente cliente=new Cliente();
        String clienteAsJsonString=new ObjectMapper().writeValueAsString(cliente);

        when(clienteService.find(1)).thenReturn(cliente);

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
        cliente.setNome("Novo Cliente");
        cliente.setNif("123456789");

        when(this.clienteService.insert(cliente)).thenReturn(cliente);

        String clienteAsJsonString=new ObjectMapper().writeValueAsString(cliente);

        mockMvc.perform(post("/cliente").content(clienteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        Cliente clienteExistente=new Cliente();
        clienteExistente.setNome("Novo Cliente");
        clienteExistente.setNif("123456789");
        String clienteExistenteAsJsonString=new ObjectMapper().writeValueAsString(clienteExistente);

        mockMvc.perform(post("/cliente").content(clienteExistenteAsJsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

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
