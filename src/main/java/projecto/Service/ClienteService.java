package projecto.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projecto.Repositories.ClienteRepository;
import projecto.Service.Exception.DataIntegrityException;
import projecto.Service.Exception.ObjectNotFoundException;
import projecto.model.Cliente;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        return obj;
    }
    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateDadosCliente(newObj, obj);
        return clienteRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }
    private void updateDadosCliente(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setNif(obj.getNif());
    }


}
