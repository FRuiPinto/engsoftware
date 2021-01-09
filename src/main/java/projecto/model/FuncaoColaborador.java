package projecto.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;


@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "funcao")
public class FuncaoColaborador {

    private static final long versionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod;
    private String nomeFuncao;
    private Double valorHora;

    public FuncaoColaborador(Integer cod , String nomeFuncao , Double valorHora){
        this.cod = cod;
        this.nomeFuncao = nomeFuncao;
        this.valorHora = valorHora;
    }

    public FuncaoColaborador() {

    }
}
