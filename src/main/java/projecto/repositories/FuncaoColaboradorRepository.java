package projecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecto.model.FuncaoColaborador;

@Repository
public interface FuncaoColaboradorRepository extends JpaRepository<FuncaoColaborador, Integer> {
}
