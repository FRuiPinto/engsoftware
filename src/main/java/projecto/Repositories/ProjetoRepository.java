package projecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecto.model.Projeto;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    @Query(
            value = "SELECT P FROM Projeto as P where P.cliente.id = :id"
    )
    List<Projeto> projetoCliente(Integer id);


    /*
    SELECT sum(f.valor_hora) FROM COLABORADOR c, TAREFA t, FUNCAO f
WHERE c.id_colaborador=t.id_colaborador
AND f.cod=c.funcao
AND t.id_projeto=5;


     */

    @Query(
            value = "  SELECT sum(f.valorHora) FROM Colaborador as c, Tarefa as t, FuncaoColaborador as f WHERE c.id=t.colaborador.id AND f.cod=c.funcao AND t.projeto.id=:id"
    )
    Double projetoValor(Integer id);
}
