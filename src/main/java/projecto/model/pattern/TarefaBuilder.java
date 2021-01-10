package projecto.model.pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import projecto.model.Colaborador;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.model.TarefaEvolucao;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class TarefaBuilder {

    private Tarefa tarefa;

    public TarefaBuilder(Projeto projeto) {
        this.tarefa = new Tarefa();
        this.tarefa.setProjeto(projeto);
    }

    public TarefaBuilder setDataInicio(LocalDate dtIniPrevisto){
        this.tarefa.setDtIniPrevisto(dtIniPrevisto);
        return this;
    }

    public TarefaBuilder setDataFim(LocalDate dtFimPrevisto){
        this.tarefa.setDtFimPrevisto(dtFimPrevisto);
        return this;
    }

    public TarefaBuilder setHorasPrevistas(Integer horasPrevistas){
        this.tarefa.setHorasPrevistas(horasPrevistas);
        return this;
    }

    public TarefaBuilder setTarefaEvolucao(TarefaEvolucao tarefaEvolucao){
        this.tarefa.setTarefaEvolucao(tarefaEvolucao);
        return this;
    }
    public TarefaBuilder setDescricao(String descricao){
        this.tarefa.setDescricao(descricao);
        return this;
    }
    public TarefaBuilder setAtivo(Boolean ativo){
        this.tarefa.setAtivo(ativo);
        return this;
    }
    public TarefaBuilder setColaborador(Colaborador colaborador){
        this.tarefa.setColaborador(colaborador);
        return this;
    }
    public Tarefa build() {
        return this.tarefa;
    }

    public static void main(String[] args) {
        Tarefa nova = new TarefaBuilder(new Projeto()).build();
    }
}
