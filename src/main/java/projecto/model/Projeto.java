package projecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

    private static final long versionUID = 1L;
    @EqualsAndHashCode.Include

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProjeto")
    private Integer id ;

    @Column(name = "descricao" , nullable = false)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "projeto")
    private Set<Tarefa> listaTarefas = new LinkedHashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    public Projeto(){}

    public Projeto( String descricao , Cliente cliente){
        this.descricao=descricao;
        this.cliente=cliente;
        this.ativo=true;
    }

    public void addTarefa(Tarefa tarefa){
        this.listaTarefas.add(tarefa);
    }
    public void removeTarefa(Tarefa tarefa){
        this.listaTarefas.remove(tarefa);
    }

}
