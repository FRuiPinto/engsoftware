package projecto.service;

import projecto.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Optional<Cliente> find(Integer id);

    List<Cliente> findAll();

    Optional<Cliente> insert(Cliente client);

    Optional<Cliente> update(Cliente client);

    Optional<Cliente>  delete(Integer id);

}
