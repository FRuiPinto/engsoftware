package projecto.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjetoNewDTO {

    private static final long versionUID = 1L;

    private Integer id;
    private String descricao;
    private LocalDate dtIniPrevisto;
    private LocalDate dtFimPrevisto;

    public ProjetoNewDTO(Integer id, String projetoDescricao, LocalDate dtIniPrevisto, LocalDate dtFimPrevisto){
        this.id = id;
        this.descricao = projetoDescricao;
        this.dtIniPrevisto = dtIniPrevisto;
        this.dtFimPrevisto = dtFimPrevisto;
    }
}
