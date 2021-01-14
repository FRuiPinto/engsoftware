package projecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecto.model.Cliente;
import projecto.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl( ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Cliente> find(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> insert(Cliente client) {
        Cliente cliente1 =   clienteRepository.save(client);
        return Optional.of(cliente1);
    }

    @Override
    public Optional<Cliente> update(Cliente client) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(client.getId());
        if(optionalCliente.isPresent()){
            optionalCliente.get().setNif(client.getNif());
            optionalCliente.get().setNome(client.getNome());
            clienteRepository.save(optionalCliente.get());
            return optionalCliente;
        }
        return optionalCliente;
    }

    @Override
    public Optional<Cliente> delete(Integer id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(optionalCliente.isPresent()){
            clienteRepository.deleteById(id);
            return optionalCliente;
        }
       return Optional.empty();
    }

}
