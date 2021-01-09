package projecto.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@Embeddable
public class TarefaEvolucao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "horasexecutadas")
    private Integer horasExecutadas  ;

    @Column(name = "percentagemhoras")
    private Double perceExecutadas  ;

    public TarefaEvolucao(){
        this.perceExecutadas= 0.00;
        this.horasExecutadas=0;
    }

    public void setHorasExecutadasTemp(Integer horas){
        this.horasExecutadas += horas;
    }
    public void setPerceExecutadasTemp(Double perceExecutadas){
        this.perceExecutadas += perceExecutadas;
    }
}
