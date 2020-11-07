package projecto.model.DTO;

import lombok.Data;

@Data
public class ProjetoNewDTO {

    private static final long versionUID = 1L;

    private Integer id;
    private String descricao;

    public ProjetoNewDTO(Integer id, String projetoDescricao){
        this.id = id;
        this.descricao = projetoDescricao;
    }
}
