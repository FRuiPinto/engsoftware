package projecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projecto.repositories.ClienteRepository;
import projecto.repositories.ProjetoRepository;
import projecto.service.exception.DataIntegrityException;
import projecto.service.exception.ObjectNotFoundException;
import projecto.model.Cliente;
import projecto.model.dto.ProjetoNewDTO;
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
        Optional<Projeto> proj = projetoRepository.findById(id);
        return proj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id ));
    }

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Projeto insert(ProjetoNewDTO proj) {
        Projeto p1 = fromDTO(proj);
        p1 = projetoRepository.save(p1);
        return p1;
    }

    public Projeto update(Projeto proj) {
        Projeto newObj = find(proj.getId());
        updateDadosProjecto(newObj, proj);
        return projetoRepository.save(newObj);
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

    public Projeto fromDTO(ProjetoNewDTO proj){
        Cliente cli1 = clienteRepository.getOne(proj.getId());
        Projeto p1 = new Projeto(proj.getDescricao(),cli1,proj.getDtIniPrevisto(),proj.getDtFimPrevisto());
        cli1.addProjecto(p1);
        return p1;
    }
    private void updateDadosProjecto(Projeto newObj, Projeto proj) {
        newObj.setDescricao(proj.getDescricao());
    }

}
