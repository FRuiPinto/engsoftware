package projecto.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import projecto.Repositories.ColaboradorRepository;
import projecto.Service.Exception.DataIntegrityException;
import projecto.Service.Exception.ObjectNotFoundException;
import projecto.model.Cliente;
import projecto.model.Colaborador;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {


    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador find(Integer id) {
        Optional<Colaborador> obj = colaboradorRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Colaborador.class.getName()));
    }

    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }

    public Colaborador insert(Colaborador obj) {
        obj.setId(null);
        obj = colaboradorRepository.save(obj);
        return obj;
    }
    public Colaborador update(Colaborador obj) {
        Colaborador newObj = find(obj.getId());
        updateDadosColaborador(newObj, obj);
        return colaboradorRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            colaboradorRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }
    private void updateDadosColaborador(Colaborador newObj, Colaborador obj) {
        newObj.setNome(obj.getNome());
        newObj.setFuncao(obj.getFuncao());
    }
}
