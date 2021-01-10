package projecto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import projecto.model.Cliente;
import projecto.repositories.ProjetoRepository;
import projecto.model.Projeto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjetoControladorTest {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Test
    void find() {
        insert();
        Optional<Projeto> proj = projetoRepository.findById(6);
        assertEquals(6, proj.get().getId());
    }

    @Test
    void findAll() {

        List<Projeto> projectos = projetoRepository.findAll();

        assertEquals(7, projectos.size());

        insert();
        assertEquals(8, projetoRepository.count());

        projectos = projetoRepository.findAll();

        assertEquals(8, projectos.size());
    }

    @Test
    void insert() {

        Projeto projeto = new Projeto("Projecto Novo", null, LocalDate.of(2020, 9, 1), LocalDate.of(2021, 3, 31));

        assertEquals(7, projetoRepository.count());

        projetoRepository.save(projeto);

        assertEquals(8, projetoRepository.count());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        insert();
        assertEquals(8, projetoRepository.count());
        projetoRepository.delete(projetoRepository.getOne(1));

        assertEquals(7, projetoRepository.count());
    }

    @Test
    void findProjetoInfoPreco() {
    }

    @Test
    void findProjetoInfoTempo() {
    }
}
