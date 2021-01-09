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

    @Query(
            value = "  SELECT sum(f.valorHora) FROM Colaborador as c, Tarefa as t, FuncaoColaborador as f WHERE c.id=t.colaborador.id AND f.cod=c.funcao AND t.projeto.id=:id"
    )
    Double projetoValor(Integer id);

    @Query(
            value = "select sum(t.horasPrevistas) from Tarefa as t where t.projeto.id = :id"
    )
    Double projectoTempoHorasPrevistas(Integer id);

    @Query(
            value = "select  sum(t.tarefaEvolucao.horasExecutadas)  from Tarefa as t where t.projeto.id = :id"
    )
    Double projectoTempoHorasHorasExecutadas(Integer id);

    @Query(
            value = "select sum(t.tarefaEvolucao.perceExecutadas) from Tarefa as t where t.projeto.id = :id"
    )
    Double projectoTempoHorasPercentagemExecutada(Integer id);
}
