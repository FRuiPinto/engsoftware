package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projecto.Service.ProjetoService;
import projecto.model.DTO.ProjetoNewDTO;
import projecto.model.Projeto;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/projeto")
public class ProjetoControlador {
    @Autowired
    private ProjetoService projetoService;

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

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        projetoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
