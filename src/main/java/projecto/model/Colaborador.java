package projecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import projecto.model.Enum.Funcao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "colaborador")
public class Colaborador implements Serializable {

    private static final long versionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idColaborador")
    private Integer id;
    @Column(name = "nome")
    private String nome;

    @Column(name = "funcao")
    private Integer funcao;

    @JsonIgnore
    @OneToMany(mappedBy = "colaborador")
    private Set<Tarefa> listaTarefas = new LinkedHashSet<>();

    @Column(name = "ativo",nullable = false)
    private Boolean ativo;

    public Colaborador(){}

    public Colaborador(String nome, Integer funcao) {
        this.nome=nome;
        //  this.funcao = funcao.getCod();
        this.funcao = funcao;
        this.ativo = true;
    }
    public void addTarefa(Tarefa tarefa){
        this.listaTarefas.add(tarefa);
    }
    public void removeTarefa(Tarefa tarefa){
        this.listaTarefas.remove(tarefa);
    }
}
