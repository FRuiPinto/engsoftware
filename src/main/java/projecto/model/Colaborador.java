package projecto.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "colaborador" ,cascade = CascadeType.ALL)

    private Set<Tarefa> listaTarefas = new LinkedHashSet<>();

    @Column(name = "ativo",nullable = false)
    private Boolean ativo;

    public Colaborador(){}

    public Colaborador(String nome, Integer funcao) {
        this.nome=nome;
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
