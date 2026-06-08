package br.com.orionbeacon.orion_beacon_api.controller;
import jakarta.validation.Valid;
import br.com.orionbeacon.orion_beacon_api.dto.AnaliseDTO;
import br.com.orionbeacon.orion_beacon_api.entity.Analise;
import br.com.orionbeacon.orion_beacon_api.entity.AreaAnalisada;
import br.com.orionbeacon.orion_beacon_api.service.AnaliseService;
import br.com.orionbeacon.orion_beacon_api.service.AreaAnalisadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analises")
public class AnaliseController {

    private final AnaliseService analiseService;
    private final AreaAnalisadaService areaService;

    public AnaliseController(AnaliseService analiseService, AreaAnalisadaService areaService) {
        this.analiseService = analiseService;
        this.areaService = areaService;
    }

    @GetMapping
    public ResponseEntity<List<Analise>> listarTodos() {
        return ResponseEntity.ok(analiseService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Analise> buscarPorId(@PathVariable Long id) {
        Analise analise = analiseService.buscarPorId(id);

        if (analise == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(analise);
    }

    @PostMapping
    public ResponseEntity<Analise> criar(@Valid @RequestBody AnaliseDTO dto) {
        AreaAnalisada area = areaService.buscarPorId(dto.areaId());

        if (area == null) {
            return ResponseEntity.badRequest().build();
        }

        Analise analise = new Analise();
        analise.setClassificacao(dto.classificacao());
        analise.setObservacao(dto.observacoes());
        analise.setDataAnalise(dto.dataAnalise());
        analise.setArea(area);

        Analise salva = analiseService.salvar(analise);

        return ResponseEntity.status(201).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Analise> atualizar(@PathVariable Long id, @Valid @RequestBody AnaliseDTO dto) {
        Analise existente = analiseService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        AreaAnalisada area = areaService.buscarPorId(dto.areaId());

        if (area == null) {
            return ResponseEntity.badRequest().build();
        }

        existente.setClassificacao(dto.classificacao());
        existente.setObservacao(dto.observacoes());
        existente.setDataAnalise(dto.dataAnalise());
        existente.setArea(area);

        Analise atualizada = analiseService.salvar(existente);

        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Analise existente = analiseService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        analiseService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}