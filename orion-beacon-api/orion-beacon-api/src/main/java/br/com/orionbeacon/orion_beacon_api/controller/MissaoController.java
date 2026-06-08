package br.com.orionbeacon.orion_beacon_api.controller;
import jakarta.validation.Valid;
import br.com.orionbeacon.orion_beacon_api.dto.MissaoDTO;
import br.com.orionbeacon.orion_beacon_api.entity.AreaAnalisada;
import br.com.orionbeacon.orion_beacon_api.entity.Missao;
import br.com.orionbeacon.orion_beacon_api.service.AreaAnalisadaService;
import br.com.orionbeacon.orion_beacon_api.service.MissaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService missaoService;
    private final AreaAnalisadaService areaService;

    public MissaoController(MissaoService missaoService, AreaAnalisadaService areaService) {
        this.missaoService = missaoService;
        this.areaService = areaService;
    }

    @GetMapping
    public ResponseEntity<List<Missao>> listarTodos() {
        return ResponseEntity.ok(missaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Missao> buscarPorId(@PathVariable Long id) {
        Missao missao = missaoService.buscarPorId(id);

        if (missao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(missao);
    }

    @PostMapping
    public ResponseEntity<Missao> criar(@Valid @RequestBody MissaoDTO dto) {
        AreaAnalisada area = areaService.buscarPorId(dto.areaId());

        if (area == null) {
            return ResponseEntity.badRequest().build();
        }

        Missao missao = new Missao();
        missao.setNome(dto.nome());
        missao.setStatus(dto.status());
        missao.setArea(area);

        Missao salva = missaoService.salvar(missao);

        return ResponseEntity.status(201).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Missao> atualizar( @PathVariable Long id, @Valid @RequestBody MissaoDTO dto) {
        Missao existente = missaoService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        AreaAnalisada area = areaService.buscarPorId(dto.areaId());

        if (area == null) {
            return ResponseEntity.badRequest().build();
        }

        existente.setNome(dto.nome());
        existente.setStatus(dto.status());
        existente.setArea(area);

        Missao atualizada = missaoService.salvar(existente);

        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Missao existente = missaoService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        missaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}