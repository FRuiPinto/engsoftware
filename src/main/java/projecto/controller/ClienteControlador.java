package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projecto.Repositories.ProjetoRepository;
import projecto.Service.ClienteService;
import projecto.model.Cliente;
import projecto.model.Projeto;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/cliente")
public class ClienteControlador {

    /**
     insert into cliente (nome,tipocliente) values('Rui','123312312');
     insert into cliente (nome,tipocliente) values('Fabio','1234232');
     select * from cliente;
     */


    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProjetoRepository projetoRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente obj = clienteService.find(id);
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> colaboradores = clienteService.findAll();
        return ResponseEntity.ok().body(colaboradores);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Cliente obj) {
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Cliente obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/clienteProject/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Projeto>> clientesProjecto(@PathVariable Integer id) {
        List<Projeto> projetos = projetoRepository.projetoCliente(id);
        return ResponseEntity.ok().body(projetos);
    }
    @RequestMapping(value = "/projeto/{id}/valor", method = RequestMethod.GET)
    public ResponseEntity<String> clientesProjectoValor(@PathVariable Integer id) {
        Double valor = projetoRepository.projetoValor(id);
        String resposta = "O valor do projeto "+ id + " é de " + valor;
        return ResponseEntity.ok().body(resposta);
    }
}
