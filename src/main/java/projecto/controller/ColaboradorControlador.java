package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        Colaborador Colab = colaboradorService.find(id);
        return ResponseEntity.ok().body(Colab);
    }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResponseEntity<List<Colaborador>> findAll() {
        List<Colaborador> colaboradores = colaboradorService.findAll();
        return ResponseEntity.ok().body(colaboradores);
    }
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Colaborador Colab) {
        Colab = colaboradorService.insert(Colab);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(Colab.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Colaborador Colab, @PathVariable Integer id) {
        Colab.setId(id);
        Colab = colaboradorService.update(Colab);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        colaboradorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
