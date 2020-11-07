package projecto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {

    private static final long versionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTarefa")
    private Integer id;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "datainicio")
    private LocalDate dtIniPrevisto;

    @Column(name = "datafim")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dtFimPrevisto;

    @Column(name = "horasprevistas")
    private Integer horasPrevistas;

    @Column(name = "descricao")
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idProjeto")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "idColaborador")
    private Colaborador colaborador;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Embedded
    private TarefaEvolucao tarefaEvolucao;

    public Tarefa() {
    }

    public Tarefa(LocalDate inicio, LocalDate fim, String descricao, Colaborador col, Projeto projeto) {
        this.dtIniPrevisto = inicio;
        this.dtFimPrevisto = fim;
        this.descricao = descricao;
        this.projeto = projeto;
        this.colaborador = col;
        this.ativo = true;
    }
    public Tarefa(LocalDate inicio, LocalDate fim, String descricao, Colaborador col, Projeto projeto, Integer horasPrevistas) {
        this.dtIniPrevisto = inicio;
        this.dtFimPrevisto = fim;
        this.descricao = descricao;
        this.projeto = projeto;
        this.colaborador = col;
        this.horasPrevistas=horasPrevistas;
        col.getListaTarefas().add(this);
        this.ativo = true;

    }
}