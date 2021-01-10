package projecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projecto.model.Tarefa;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    @Query( value = "SELECT T from Tarefa T where T.colaborador.id = :idColaborador")
    List<Tarefa> findAllByColaboradorId(Integer idColaborador);
@Query("SELECT t FROM Tarefa t WHERE "
        +"(:ativo is null or t.ativo = :ativo) and (:descricao is null or t.descricao = :descricao)"
        +"and (:dataInicio is null or t.dtIniPrevisto >= :dataInicio) and (:dataFim is null or t.dtFimPrevisto <= :dataFim)"
        +" and (:horasPrevistasMinimas is null or t.horasPrevistas >= :horasPrevistasMinimas) and (:horasPrevistasMax is null or t.horasPrevistas <= :horasPrevistasMax)"
        +" and (:horasExecutadasMinima is null or t.tarefaEvolucao.horasExecutadas >= :horasExecutadasMinima) and (:horasexecutadasMax is null or t.tarefaEvolucao.horasExecutadas <= :horasexecutadasMax)"
        +" and (:percentagemHorasMin is null or t.tarefaEvolucao.perceExecutadas >= :percentagemHorasMin) and (:percentagemHorasMax is null or t.tarefaEvolucao.perceExecutadas <= :percentagemHorasMax)"
        +"and (:idColaborador is null or t.colaborador.id = :idColaborador) and (:idProjeto is null or t.projeto.id = :idProjeto)"
)
    List<Tarefa> filterTarefas( @Param("ativo") Boolean ativo
            ,@Param("descricao") String descricao,@Param("dataInicio") LocalDate dataInicio
            ,@Param("dataFim")LocalDate dataFim ,@Param("horasPrevistasMinimas") Integer horasPrevistasMinimas,@Param("horasPrevistasMax") Integer horasPrevistasMax
            ,@Param("horasExecutadasMinima") Integer horasExecutadasMinima,@Param("horasexecutadasMax") Integer horasexecutadasMax
            ,@Param("percentagemHorasMin") Double percentagemHorasMin,@Param("percentagemHorasMax") Double percentagemHorasMax
            ,@Param("idColaborador")Integer idColaborador,@Param("idProjeto") Integer idProjeto);

}
