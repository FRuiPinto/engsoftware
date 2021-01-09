package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projecto.service.TarefaService;
import projecto.model.dto.TarefaNewDTO;
import projecto.model.Tarefa;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value="/tarefa")
public class TarefaControlador {

    @Autowired
    private TarefaService tarefaService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Tarefa> find(@PathVariable Integer id) {
        Tarefa obj = tarefaService.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> findAll() {
        List<Tarefa> tarefas = tarefaService.findAll();
        return ResponseEntity.ok().body(tarefas);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody TarefaNewDTO obj ) {
        Tarefa t1 = tarefaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(t1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value="/{id}/{horas}", method=RequestMethod.PATCH)
    public ResponseEntity<String> uptadeHoras(@PathVariable Integer id,@PathVariable Integer horas) throws ParseException {
        String resposta = tarefaService.updateHoras(id,horas);
        return ResponseEntity.ok().body(resposta);
    }
}
