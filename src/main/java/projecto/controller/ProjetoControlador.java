package projecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecto.model.Projeto;
import projecto.model.Tarefa;
import projecto.model.dto.ProjetoNewDTO;
import projecto.service.ProjetoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/projeto")
public class ProjetoControlador {

    private final ProjetoService projetoService;

    @Autowired
    public ProjetoControlador(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }
    @GetMapping(value = "/{idProjecto}")
    private ResponseEntity<Projeto> find(@PathVariable Integer idProjecto){
        Optional<Projeto> projetoTemp = projetoService.findById(idProjecto);
        if(projetoTemp.isPresent()){
            return ResponseEntity.ok().body(projetoTemp.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping()
    private ResponseEntity<List<Projeto>> findAll(){
        List<Projeto> projetoList = projetoService.findAll();
        if(projetoList.size() > 0){
            return ResponseEntity.ok().body(projetoList);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    private ResponseEntity<Projeto> criaProjecto(@RequestBody ProjetoNewDTO projeto){
        Optional<Projeto> projetoNovo = projetoService.createProjecto(projeto);
        return projetoNovo.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @RequestMapping(value = "/{idProjecto}", method = RequestMethod.DELETE)
    private ResponseEntity<Void> deleteProjeto(@PathVariable Integer idProjecto){
        projetoService.deleteProjeto(idProjecto);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping
    private ResponseEntity<Projeto> insereTarefa(@RequestBody Tarefa tarefa){
        Optional<Projeto> projetoNovo = projetoService.insereTarefa(tarefa);
        return projetoNovo.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

