package projecto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import projecto.model.Cliente;
import projecto.model.Colaborador;
import projecto.repositories.ClienteRepository;
import projecto.repositories.ColaboradorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ColaboradorControladorTest {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Test
    void find() {
        insert();
        Optional<Colaborador> colab = colaboradorRepository.findById(1);
        assertEquals(1, colab.get().getId());
    }

    @Test
    void findAll() {
        insert();
        assertEquals(6, colaboradorRepository.count());

        List<Colaborador> colab = colaboradorRepository.findAll();

        assertEquals(6, colab.size());
    }

    @Test
    void insert() {
        Colaborador colab = new Colaborador("Colaborador Teste", 2);

        assertEquals(5, colaboradorRepository.count());

        colaboradorRepository.save(colab);

        assertEquals(6, colaboradorRepository.count());
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
        insert();
        assertEquals(6, colaboradorRepository.count());
        colaboradorRepository.delete(colaboradorRepository.getOne(6));

        assertEquals(5, colaboradorRepository.count());
    }
}