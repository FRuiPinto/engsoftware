package projecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import projecto.service.ColaboradorService;

@WebMvcTest(TarefaControlador.class)
class TarefaControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TarefaControlador tarefaControlador;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void find() throws Exception {


    }

    @Test
    void findAll() throws Exception {


    }
}
