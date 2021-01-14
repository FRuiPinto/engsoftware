package projecto.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ColaboradorTest {

    @Test
    void ColaboradorTest() {

        Colaborador novoColab = new Colaborador("Colaborador X", 3);
        assertEquals("Colaborador X", novoColab.getNome());
        assertNotEquals(2, novoColab.getFuncao());

        novoColab.setNome("Colaborador Y");
        novoColab.setFuncao(4);
        assertEquals("Colaborador Y", novoColab.getNome());
        assertEquals(4, novoColab.getFuncao());

        Tarefa tarefa = new Tarefa(LocalDate.of(2021, 6, 30), LocalDate.of(2021, 1, 1), "Software Test Engineer I", novoColab, null, 20);

        novoColab.addTarefa(tarefa);
        assertEquals(1, novoColab.getListaTarefas().size());
        novoColab.removeTarefa(tarefa);
        assertEquals(0, novoColab.getListaTarefas().size());

        assertEquals(true, novoColab.getAtivo());
        novoColab.setAtivo(false);
        assertEquals(false, novoColab.getAtivo());
    }
}