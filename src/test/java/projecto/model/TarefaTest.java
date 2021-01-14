package projecto.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void TarefaTest() {

        Colaborador novoColab = new Colaborador("Colaborador X", 3);
        Projeto novoProj = new Projeto("Projeto 1",null,LocalDate.of(2021, 1, 1),LocalDate.of(2021, 6, 30));

        Tarefa tarefa = new Tarefa(LocalDate.of(2021, 6, 30), LocalDate.of(2021, 1, 1), "Software Test Engineer I", novoColab, novoProj, 20);
        assertEquals("Colaborador X", tarefa.getColaborador().getNome());
        assertEquals("Projeto 1", tarefa.getProjeto().getDescricao());
        assertNotEquals(1, tarefa.getProjeto().getListaTarefas().size());

        tarefa.updateHoras(10);
        assertEquals(10, tarefa.getTarefaEvolucao().getHorasExecutadas());
        tarefa.getTarefaEvolucao().setHorasExecutadasTemp(10);
        assertEquals(20, tarefa.getTarefaEvolucao().getHorasExecutadas());
        tarefa.getTarefaEvolucao().setHorasExecutadas(0);
        assertEquals(0, tarefa.getTarefaEvolucao().getHorasExecutadas());


        assertEquals(10, tarefa.getTarefaEvolucao().getHorasExecutadas());
        tarefa.getTarefaEvolucao().setHorasExecutadasTemp(10);
        assertEquals(20, tarefa.getTarefaEvolucao().getHorasExecutadas());
        tarefa.getTarefaEvolucao().setHorasExecutadas(0);
        assertEquals(0, tarefa.getTarefaEvolucao().getHorasExecutadas());


        assertEquals(true, tarefa.getAtivo());
        tarefa.setAtivo(false);
        assertEquals(false, tarefa.getAtivo());
    }
}