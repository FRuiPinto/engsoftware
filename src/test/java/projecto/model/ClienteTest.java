package projecto.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
class ClienteTest {

    @Test
    void clienteTeste() {
        Cliente novoCliente = new Cliente("Tiago", "nif123");
        assertEquals("Tiago", novoCliente.getNome());
        assertNotEquals("nif1234", novoCliente.getNif());

        novoCliente.setNome("Tiago Correia");
        novoCliente.setNif("nif1234");

        assertEquals("Tiago Correia", novoCliente.getNome());
        assertEquals("nif1234", novoCliente.getNif());
        Projeto p5 = new Projeto("Aplicação mobile", novoCliente, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        assertNotEquals(1, novoCliente.getListaProjectos().size());
        novoCliente.addProjecto(p5);
        assertEquals(1, novoCliente.getListaProjectos().size());
        novoCliente.removeProjecto(p5);
        assertEquals(0, novoCliente.getListaProjectos().size());
        assertEquals(true, novoCliente.getAtivo());
        novoCliente.setAtivo(false);
        assertEquals(false, novoCliente.getAtivo());
    }
}

