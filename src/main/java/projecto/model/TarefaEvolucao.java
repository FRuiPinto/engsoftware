package projecto.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Embeddable
public class TarefaEvolucao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "horasexecutadas")
    private Integer horasExecutadas  ;

    @Column(name = "percentagemhoras")
    private Integer perceExecutadas  ;

    public TarefaEvolucao(){
        this.perceExecutadas=0;
        this.horasExecutadas=0;
    }

    public void setHorasExecutadas(Integer horas){
        this.horasExecutadas += horas;
    }
}
