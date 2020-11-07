package projecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecto.model.Colaborador;
@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
