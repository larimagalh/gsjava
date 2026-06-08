package br.com.orionbeacon.orion_beacon_api.controller;
import jakarta.validation.Valid;
import br.com.orionbeacon.orion_beacon_api.dto.AreaAnalisadaDTO;
import br.com.orionbeacon.orion_beacon_api.entity.AreaAnalisada;
import br.com.orionbeacon.orion_beacon_api.entity.Coordenada;
import br.com.orionbeacon.orion_beacon_api.entity.CorpoCeleste;
import br.com.orionbeacon.orion_beacon_api.service.AreaAnalisadaService;
import br.com.orionbeacon.orion_beacon_api.service.CorpoCelesteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas-analisadas")
public class AreaAnalisadaController {

    private final AreaAnalisadaService areaService;
    private final CorpoCelesteService corpoCelesteService;

    public AreaAnalisadaController(
            AreaAnalisadaService areaService,
            CorpoCelesteService corpoCelesteService
    ) {
        this.areaService = areaService;
        this.corpoCelesteService = corpoCelesteService;
    }

    @GetMapping
    public ResponseEntity<List<AreaAnalisada>> listarTodos() {
        return ResponseEntity.ok(areaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaAnalisada> buscarPorId(@PathVariable Long id) {
        AreaAnalisada area = areaService.buscarPorId(id);

        if (area == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(area);
    }

    @PostMapping
    public ResponseEntity<AreaAnalisada> criar(@Valid @RequestBody AreaAnalisadaDTO dto) {
        CorpoCeleste corpoCeleste = corpoCelesteService.buscarPorId(dto.corpoCelesteId());

        if (corpoCeleste == null) {
            return ResponseEntity.badRequest().build();
        }

        AreaAnalisada area = new AreaAnalisada();
        area.setNome(dto.nome());
        area.setRegiao(dto.regiao());
        area.setCoordenada(new Coordenada(dto.latitude(), dto.longitude()));
        area.setCorpoCeleste(corpoCeleste);

        AreaAnalisada salva = areaService.salvar(area);

        return ResponseEntity.status(201).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaAnalisada> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AreaAnalisadaDTO dto
    ) {
        AreaAnalisada existente = areaService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        CorpoCeleste corpoCeleste = corpoCelesteService.buscarPorId(dto.corpoCelesteId());

        if (corpoCeleste == null) {
            return ResponseEntity.badRequest().build();
        }

        existente.setNome(dto.nome());
        existente.setRegiao(dto.regiao());
        existente.setCoordenada(new Coordenada(dto.latitude(), dto.longitude()));
        existente.setCorpoCeleste(corpoCeleste);

        AreaAnalisada atualizada = areaService.salvar(existente);

        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        AreaAnalisada existente = areaService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        areaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
