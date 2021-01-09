package projecto.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projecto.Repositories.ClienteRepository;
import projecto.Repositories.ProjetoRepository;
import projecto.Service.Exception.DataIntegrityException;
import projecto.Service.Exception.ObjectNotFoundException;
import projecto.model.Cliente;
import projecto.model.DTO.ProjetoNewDTO;
import projecto.model.Projeto;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {


    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    public Projeto find(Integer id) {
        Optional<Projeto> obj = projetoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id ));
    }

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Projeto insert(ProjetoNewDTO obj) {
        Projeto p1 = fromDTO(obj);
        p1 = projetoRepository.save(p1);
        return p1;
    }

    public void delete(Integer id) {
        find(id);
        try {
            projetoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public Projeto fromDTO(ProjetoNewDTO obj){
        Cliente cli1 = clienteRepository.getOne(obj.getId());
        Projeto p1 = new Projeto(obj.getDescricao(),cli1,obj.getDtIniPrevisto(),obj.getDtFimPrevisto());
        cli1.addProjecto(p1);
        return p1;
    }

}
