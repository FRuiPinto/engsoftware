package projecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projecto.repositories.ColaboradorRepository;
import projecto.service.exception.DataIntegrityException;
import projecto.service.exception.ObjectNotFoundException;
import projecto.model.Colaborador;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {


    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public Colaborador find(Integer id) {
        Optional<Colaborador> colab = colaboradorRepository.findById(id);
        return colab.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Colaborador.class.getName()));
    }
    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }
    public Colaborador insert(Colaborador colab) {
        colab.setId(null);
        colab = colaboradorRepository.save(colab);
        return colab;
    }
    public Colaborador update(Colaborador colab) {
        Colaborador newObj = find(colab.getId());
        updateDadosColaborador(newObj, colab);
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
    private void updateDadosColaborador(Colaborador newObj, Colaborador colab) {
        newObj.setNome(colab.getNome());
        newObj.setFuncao(colab.getFuncao());
    }
}
