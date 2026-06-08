package br.com.orionbeacon.orion_beacon_api.controller;

import br.com.orionbeacon.orion_beacon_api.dto.CorpoCelesteDTO;
import br.com.orionbeacon.orion_beacon_api.entity.CorpoCeleste;
import br.com.orionbeacon.orion_beacon_api.service.CorpoCelesteService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/corpos-celestes")
public class CorpoCelesteController {

    private final CorpoCelesteService service;

    public CorpoCelesteController(CorpoCelesteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CorpoCeleste>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CorpoCeleste>> buscarPorId(@PathVariable Long id) {
        CorpoCeleste corpoCeleste = service.buscarPorId(id);

        if (corpoCeleste == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<CorpoCeleste> resource = EntityModel.of(corpoCeleste);

        resource.add(linkTo(methodOn(CorpoCelesteController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(CorpoCelesteController.class).listarTodos()).withRel("listar-corpos-celestes"));

        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<CorpoCeleste> criar(@Valid @RequestBody CorpoCelesteDTO dto) {
        CorpoCeleste corpoCeleste = new CorpoCeleste();
        corpoCeleste.setNome(dto.nome());

        CorpoCeleste salvo = service.salvar(corpoCeleste);

        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorpoCeleste> atualizar(@PathVariable Long id, @Valid @RequestBody CorpoCelesteDTO dto) {
        CorpoCeleste existente = service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNome(dto.nome());

        CorpoCeleste atualizado = service.salvar(existente);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        CorpoCeleste existente = service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}