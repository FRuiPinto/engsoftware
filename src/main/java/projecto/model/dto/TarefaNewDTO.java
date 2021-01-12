package projecto.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaNewDTO {
    private Integer idprojeto;
    private LocalDate dtini;
    private LocalDate dtfim;
    private String descricao;
    private Integer horasPrevistas;
    public TarefaNewDTO(){}
    public TarefaNewDTO( LocalDate inicio , LocalDate fim , String descricao , Integer projeto, Integer horasPrevistas){
        this.dtini = inicio;
        this.dtfim = fim ;
        this.idprojeto = projeto;
        this.descricao = descricao;
        this.horasPrevistas = horasPrevistas;
    }
}
