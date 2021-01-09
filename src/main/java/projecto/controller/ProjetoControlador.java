package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projecto.repositories.FuncaoColaboradorRepository;
import projecto.service.ProjetoService;
import projecto.model.dto.ProjetoNewDTO;
import projecto.model.FuncaoColaborador;
import projecto.model.Projeto;
import projecto.model.Tarefa;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/projeto")
public class ProjetoControlador {
    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private FuncaoColaboradorRepository funcaoColaboradorRepository;
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Projeto> find(@PathVariable Integer id) {
        Projeto obj = projetoService.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseEntity<List<Projeto>> findAll() {
        List<Projeto> projetos = projetoService.findAll();
        return ResponseEntity.ok().body(projetos);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ProjetoNewDTO obj ) {
        Projeto p1 = projetoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(p1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Projeto obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = projetoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        projetoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value ="/{id}/valor")
    public String findProjetoInfoPreco(@PathVariable Integer id){
        Projeto p1 = projetoService.find(id);
        Double valor = 0.0;
        if(p1.getId() != null){
           for(Tarefa t : p1.getListaTarefas()){
               //Funcao f = Funcao.toEnum(t.getColaborador().getFuncao().getValorHora());
               Optional<FuncaoColaborador> a = funcaoColaboradorRepository.findById(t.getColaborador().getId());
               valor +=a.get().getValorHora() ;
           }
           return "Projecto " + p1.getId() + " tem um valor de  " + valor;
        }
        return "Projecto não encontrado";
    }
    public String findProjetoInfoTempo(Integer id){
        Projeto p1 = projetoService.find(id);
        Double valor = 0.0;
        if(p1 != null){
           return "Projecto tem uma duração de " + p1.getId() + " tem um valor de  " + valor;
        }
        return "Projecto não encontrado";
    }
}
