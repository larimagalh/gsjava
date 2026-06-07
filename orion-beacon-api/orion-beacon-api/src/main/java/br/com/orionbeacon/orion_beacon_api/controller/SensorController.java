package br.com.orionbeacon.orion_beacon_api.controller;

import br.com.orionbeacon.orion_beacon_api.dto.SensorDTO;
import br.com.orionbeacon.orion_beacon_api.entity.Sensor;
import br.com.orionbeacon.orion_beacon_api.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Sensor>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> buscarPorId(@PathVariable Long id) {
        Sensor sensor = service.buscarPorId(id);

        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sensor);
    }

    @PostMapping
    public ResponseEntity<Sensor> criar(@RequestBody SensorDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setNome(dto.nome());
        sensor.setTipo(dto.tipo());
        sensor.setDescricao(dto.descricao());

        Sensor salvo = service.salvar(sensor);

        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> atualizar(@PathVariable Long id, @RequestBody SensorDTO dto) {
        Sensor existente = service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNome(dto.nome());
        existente.setTipo(dto.tipo());
        existente.setDescricao(dto.descricao());

        Sensor atualizado = service.salvar(existente);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Sensor existente = service.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}