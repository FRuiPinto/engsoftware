package projecto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import projecto.Repositories.ClienteRepository;
import projecto.model.Cliente;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteControladorTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void find() {
    }

    @Test
    void findAll() {
        insert();
        assertEquals(6, clienteRepository.count());

        List<Cliente> clientes = clienteRepository.findAll();

        assertEquals(6, clientes.size());
    }

    @Test
    void insert() {
        Cliente cliente = new Cliente("Rui", "123456789");

        assertEquals(5, clienteRepository.count());

        clienteRepository.save(cliente);

        assertEquals(6, clienteRepository.count());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        insert();
        assertEquals(6, clienteRepository.count());
        clienteRepository.delete(clienteRepository.getOne(1));

        assertEquals(5, clienteRepository.count());
    }

    @Test
    void clientesProjecto() {
    }

    @Test
    void clientesProjectoValor() {
    }

    @Test
    void clientesProjectoTemp() {
    }
}