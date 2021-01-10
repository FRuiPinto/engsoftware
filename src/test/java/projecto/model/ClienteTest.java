package projecto.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {


    void clienteTeste() {
        Cliente novoCliente = new Cliente("Tiago", "nif123");
        assertEquals("tiago", novoCliente.getNome());
        assertEquals("nif1234", novoCliente.getNif());

        novoCliente.setNome("Tiago Correia");
        novoCliente.setNif("nif1234");

        assertEquals("Tiago Correia", novoCliente.getNome());
        assertEquals("nif1234", novoCliente.getNif());
        Projeto p5 = new Projeto("Aplicação mobile", novoCliente, LocalDate.of(2020, 9, 20), LocalDate.of(2020, 9, 20));
        assertEquals(1, novoCliente.getListaProjectos().size());
        novoCliente.addProjecto(p5);
        assertEquals(1, novoCliente.getListaProjectos().size());
        novoCliente.removeProjecto(p5);
        assertEquals(0, novoCliente.getListaProjectos().size());
        assertEquals(true, novoCliente.getAtivo());
        novoCliente.setAtivo(false);
        assertEquals(false, novoCliente.getAtivo());
    }
}

