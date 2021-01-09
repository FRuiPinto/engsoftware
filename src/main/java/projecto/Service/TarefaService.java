package projecto.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projecto.Repositories.ColaboradorRepository;
import projecto.Repositories.ProjetoRepository;
import projecto.Repositories.TarefaRepository;
import projecto.Service.Exception.DataIntegrityException;
import projecto.Service.Exception.ObjectNotFoundException;
import projecto.model.Colaborador;
import projecto.model.DTO.TarefaNewDTO;
import projecto.model.Projeto;
import projecto.model.Tarefa;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {


    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa find(Integer id) {
        Optional<Tarefa> obj = tarefaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Projeto.class.getName()));
    }

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Tarefa insert(TarefaNewDTO obj) {
        Tarefa t1 = fromDTO(obj);
        t1 = tarefaRepository.save(t1);
        return t1;
    }

    public void delete(Integer id) {
        find(id);
        try {
            tarefaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
        }
    }

    public Tarefa fromDTO(TarefaNewDTO obj){
//LocalDate inicio, LocalDate fim, String descricao, Colaborador col, Projeto projeto, Integer horasPrevistas
        Colaborador clo = colaboradorRepository.getOne(obj.getIdcolaborador());
        Projeto p1 = projetoRepository.getOne(obj.getIdprojeto());
        Tarefa t1 = new Tarefa(obj.getDtini(),obj.getDtfim(),obj.getDescricao(),clo,p1);
        p1.addTarefa(t1);
        clo.addTarefa(t1);
        return t1;
    }

    public String updateHoras(Integer id , Integer horas) throws ParseException {
        Optional<Tarefa> t = tarefaRepository.findById(id);
        double percentagem = (double)(horas*100)/t.get().getHorasPrevistas();
        System.out.println(horas);
        System.out.println(percentagem);
        int horasAntigas = t.get().getTarefaEvolucao().getHorasExecutadas();
        double percentagemAntiga = t.get().getTarefaEvolucao().getPerceExecutadas();


        if(t.get().getTarefaEvolucao().getHorasExecutadas() + horas >= 100 ){
            t.get().setAtivo(false);
            t.get().getTarefaEvolucao().setPerceExecutadas(100.0);
            t.get().getTarefaEvolucao().setHorasExecutadas( t.get().getHorasPrevistas());

        }else {
                t.get().getTarefaEvolucao().setHorasExecutadasTemp(horas);
            t.get().getTarefaEvolucao().setPerceExecutadasTemp(percentagem);

        }
        tarefaRepository.save(t.get());
        String resposta = "Horas de antigas " +horasAntigas + " atualizadas para " + t.get().getTarefaEvolucao().getHorasExecutadas() + " percentagem de " + percentagemAntiga + "% passout para "+ t.get().getTarefaEvolucao().getPerceExecutadas() ;
        return resposta;
    }
}
