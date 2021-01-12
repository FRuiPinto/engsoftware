package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projecto.model.Tarefa;
import projecto.model.dto.TarefaNewDTO;
import projecto.repositories.TarefaRepository;
import projecto.service.TarefaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tarefa")
public class TarefaControlador {

    private final TarefaService tarefaService;
    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaControlador(TarefaService tarefaService, TarefaRepository tarefaRepository) {
        this.tarefaService = tarefaService;
        this.tarefaRepository = tarefaRepository;
    }

    @GetMapping(value = "/{tarefaId}")
    private ResponseEntity<Tarefa> find(@PathVariable Integer tarefaId){
        Optional<Tarefa> tarefaTemp = tarefaService.findById(tarefaId);
        if(tarefaTemp.isPresent()){
            return ResponseEntity.ok().body(tarefaTemp.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    private ResponseEntity<List<Tarefa>> findAll(){
        List<Tarefa> tarefaList = tarefaService.findAll();
        if(tarefaList.size() > 0){
            return ResponseEntity.ok().body(tarefaList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Tarefa> criaTarefa(@RequestBody TarefaNewDTO projeto){
        Optional<Tarefa> tarefaNovo = tarefaService.createTarefa(projeto);
        return tarefaNovo.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/{tarefaId}", method = RequestMethod.DELETE)
    private ResponseEntity<Void> deleteTarefa(@PathVariable Integer tarefaId){
        tarefaService.deleteTarefa(tarefaId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/updateHoras")
    private ResponseEntity<Tarefa> updateHorasTarefa(@RequestParam(value="idTarefa") Integer idTarefa,@RequestParam(value="horas") Integer horas){
        Optional<Tarefa> tarefaOptional = tarefaService.updateTarefaHoras(idTarefa,horas);
        return tarefaOptional.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/updateColaborador")
    private ResponseEntity<Tarefa> updateColadorador(@RequestParam() Integer idTarefa,@RequestParam() Integer novoColaborador,@RequestParam( required = false) Integer antigoColaborador){
        if(antigoColaborador == null){
            antigoColaborador = 0 ;
        }
        Optional<Tarefa> tarefaOptional = tarefaService.trocaColadoradores(idTarefa,novoColaborador,antigoColaborador);
        return tarefaOptional.map(value -> ResponseEntity.ok(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    private ResponseEntity<List<Tarefa>> filterTarefas(
            @RequestParam(required = false) Boolean ativo
            ,@RequestParam(required = false) String descricao,@RequestParam(required = false) LocalDate dataInicio
            ,@RequestParam(required = false)LocalDate dataFim ,@RequestParam(required = false) Integer horasPrevistasMinimas,@RequestParam(required = false) Integer horasPrevistasMax
            ,@RequestParam(required = false) Integer horasExecutadasMinima,@RequestParam(required = false) Integer horasexecutadasMax
            ,@RequestParam(required = false) Double percentagemHorasMin,@RequestParam(required = false) Double percentagemHorasMax
            ,@RequestParam(required = false)Integer idColaborador,@RequestParam(required = false) Integer idProjeto
    ){
        List<Tarefa> novaLista = tarefaRepository.filterTarefas(ativo,descricao,dataInicio,dataFim,horasPrevistasMinimas,horasPrevistasMax,horasExecutadasMinima,horasexecutadasMax,percentagemHorasMin,percentagemHorasMax,idColaborador,idProjeto);
        if(novaLista.size() >= 0){
            return ResponseEntity.ok().body(novaLista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
