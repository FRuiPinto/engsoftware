package projecto.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void ProjetoTest() {
        Projeto novoProj = new Projeto("Projeto 1",null,LocalDate.of(2021, 1, 1),LocalDate.of(2021, 6, 30));
        assertEquals("Projeto 1", novoProj.getDescricao());
        assertNotEquals(2, novoProj.getId());

        Cliente novoCliente = new Cliente("Tiago", "nif123");
        novoProj.setCliente(novoCliente);
        assertEquals("Tiago", novoProj.getCliente().getNome());
        assertNotEquals("XPTO", novoProj.getCliente().getNif());

        Colaborador novoColab = new Colaborador("Colaborador X", 3);
        Tarefa tarefa = new Tarefa(LocalDate.of(2021, 6, 30), LocalDate.of(2021, 1, 1), "Software Test Engineer I", novoColab, novoProj, 20);

        novoProj.addTarefa(tarefa);
        assertEquals(1, novoProj.getListaTarefas().size());
        novoProj.removeTarefa(tarefa);
        assertEquals(0, novoProj.getListaTarefas().size());

        assertEquals(true, novoProj.getAtivo());
        novoProj.setAtivo(false);
        assertEquals(false, novoProj.getAtivo());
    }
}