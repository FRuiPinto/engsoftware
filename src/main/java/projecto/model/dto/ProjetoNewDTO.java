package projecto.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjetoNewDTO {

    private static final long versionUID = 1L;

    private Integer idCliente;
    private String descricao;
    private LocalDate dtIniPrevisto;
    private LocalDate dtFimPrevisto;

    public ProjetoNewDTO(Integer idCliente, String projetoDescricao, LocalDate dtIniPrevisto, LocalDate dtFimPrevisto){
        this.idCliente = idCliente;
        this.descricao = projetoDescricao;
        this.dtIniPrevisto = dtIniPrevisto;
        this.dtFimPrevisto = dtFimPrevisto;
    }
    public ProjetoNewDTO(){}
}
