package br.com.orionbeacon.orion_beacon_api.controller;

import br.com.orionbeacon.orion_beacon_api.dto.CorpoCelesteDTO;
import br.com.orionbeacon.orion_beacon_api.entity.CorpoCeleste;
import br.com.orionbeacon.orion_beacon_api.service.CorpoCelesteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<CorpoCeleste> buscarPorId(@PathVariable Long id) {
        CorpoCeleste corpoCeleste = service.buscarPorId(id);

        if (corpoCeleste == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(corpoCeleste);
    }

    @PostMapping
    public ResponseEntity<CorpoCeleste> criar(@RequestBody CorpoCelesteDTO dto) {
        CorpoCeleste corpoCeleste = new CorpoCeleste();
        corpoCeleste.setNome(dto.nome());

        CorpoCeleste salvo = service.salvar(corpoCeleste);

        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorpoCeleste> atualizar(@PathVariable Long id, @RequestBody CorpoCelesteDTO dto) {
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
