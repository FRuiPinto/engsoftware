package projecto.model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TarefaNewDTO {
    private Integer idcolaborador;
    private Integer idprojeto;
    private LocalDate dtini;
    private LocalDate dtfim;
    private String descricao;
    private Integer horasPrevistas;
    //	ATIVO  	DESCRICAO  	DATAFIM  	DATAINICIO  	HORASPREVISTAS  	HORASEXECUTADAS  	PERCENTAGEMHORAS  	ID_COLABORADOR  	ID_PROJETO
    public TarefaNewDTO( LocalDate inicio , LocalDate fim , String descricao , Integer col, Integer projeto, Integer horasPrevistas){
        this.dtini = inicio;
        this.dtfim = fim ;
        this.idcolaborador = col;
        this.idprojeto = projeto;
        this.descricao = descricao;
        this.horasPrevistas = horasPrevistas;
    }
}
