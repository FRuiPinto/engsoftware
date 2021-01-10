package projecto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    @OneToMany(mappedBy = "projeto" ,cascade = CascadeType.ALL)
    private Set<Tarefa> listaTarefas = new LinkedHashSet<>();

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "datainicio")
    private LocalDate dtIniPrevisto;

    @Column(name = "datafim")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dtFimPrevisto;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    public Projeto(){}

    public Projeto( String descricao , Cliente cliente,LocalDate dtIniPrevisto, LocalDate dtFimPrevisto){
        this.descricao=descricao;
        this.cliente=cliente;
        this.ativo=true;
        this.dtFimPrevisto=dtFimPrevisto;
        this.dtIniPrevisto=dtIniPrevisto;
    }

    public Boolean addTarefa(Tarefa tarefa){
      if(!listaTarefas.contains(tarefa)){
          this.listaTarefas.add(tarefa);
          return true;
      }
      return false;
    }
    public Boolean removeTarefa(Tarefa tarefa){
        if(listaTarefas.contains(tarefa)){
            this.listaTarefas.remove(tarefa);
            return true;
        }
        return false;
    }

}
