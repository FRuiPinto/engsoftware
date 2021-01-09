package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projecto.service.ColaboradorService;
import projecto.model.Colaborador;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/colaborador")
public class ColaboradorControlador {
    /*
    INSERT INTO COLABORADOR (funcao,nome) VALUES(1,'Rui' );
    INSERT INTO COLABORADOR (funcao,nome) values(2,'Fabio');
    select * from colaborador;
    */
    @Autowired
    private ColaboradorService colaboradorService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Colaborador> find(@PathVariable Integer id) {
        Colaborador obj = colaboradorService.find(id);
        return ResponseEntity.ok().body(obj);
    }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseEntity<List<Colaborador>> findAll() {
        List<Colaborador> colaboradores = colaboradorService.findAll();
        return ResponseEntity.ok().body(colaboradores);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Colaborador obj) {
        obj = colaboradorService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Colaborador obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = colaboradorService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        colaboradorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
