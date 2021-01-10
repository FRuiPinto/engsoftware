package projecto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import projecto.repositories.ClienteRepository;
import projecto.model.Cliente;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClienteControladorTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void find() {
        insert();
        Optional<Cliente> cliente = clienteRepository.findById(6);
        assertEquals(6, cliente.get().getId());
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
        insert();
        Optional<Cliente> cli1 = clienteRepository.findById(6);
        assertEquals("123456789", cli1.get().getNif());

        cli1.get().setNif("987654321");
        clienteRepository.save(cli1.get());

        Optional<Cliente> cli2 = clienteRepository.findById(6);
        assertEquals("987654321", cli2.get().getNif());

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
