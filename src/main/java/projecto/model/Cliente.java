package projecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="cliente")
public class Cliente implements Serializable {

    private static final long versionUID = 1L;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer id;
    @Column(name = "nome" , nullable = false)
    private String nome;
    @Column(name = "nif" , unique = true , nullable = false)
    private String nif;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Projeto> listaProjectos = new LinkedHashSet<>();
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    public Cliente (){}

    public Cliente( String nome , String nif){
        this.nome=nome;
        this.nif =nif;
        this.ativo=true;
    }

    public void addProjecto(Projeto projeto){
        this.listaProjectos.add(projeto);
    }
    public void removeProjecto(Projeto projeto){
        this.listaProjectos.remove(projeto);
    }
}
