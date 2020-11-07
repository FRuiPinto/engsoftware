package projecto.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Embeddable
public class TarefaEvolucao implements Serializable {

    private static final long serialVersionUID = 1L;
    @ColumnDefault(value = "0")
    @Column(name = "horasexecutadas" )
    private Integer horasExecutadas = 0 ;

    @Column(name = "precentagemhoras")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer perceExecutadas = 0 ;

    public TarefaEvolucao(){
    }

}
