package br.com.orionbeacon.orion_beacon_api.controller;
import jakarta.validation.Valid;
import br.com.orionbeacon.orion_beacon_api.dto.LeituraSensorDTO;
import br.com.orionbeacon.orion_beacon_api.entity.Analise;
import br.com.orionbeacon.orion_beacon_api.entity.LeituraSensor;
import br.com.orionbeacon.orion_beacon_api.entity.Sensor;
import br.com.orionbeacon.orion_beacon_api.service.AnaliseService;
import br.com.orionbeacon.orion_beacon_api.service.LeituraSensorService;
import br.com.orionbeacon.orion_beacon_api.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras-sensores")
public class LeituraSensorController {

    private final LeituraSensorService leituraService;
    private final SensorService sensorService;
    private final AnaliseService analiseService;

    public LeituraSensorController(
            LeituraSensorService leituraService,
            SensorService sensorService,
            AnaliseService analiseService
    ) {
        this.leituraService = leituraService;
        this.sensorService = sensorService;
        this.analiseService = analiseService;
    }

    @GetMapping
    public ResponseEntity<List<LeituraSensor>> listarTodos() {
        return ResponseEntity.ok(leituraService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeituraSensor> buscarPorId(@PathVariable Long id) {
        LeituraSensor leitura = leituraService.buscarPorId(id);

        if (leitura == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(leitura);
    }

    @PostMapping
    public ResponseEntity<LeituraSensor> criar(@Valid @RequestBody LeituraSensorDTO dto) {
        Sensor sensor = sensorService.buscarPorId(dto.sensorId());
        Analise analise = analiseService.buscarPorId(dto.analiseId());

        if (sensor == null || analise == null) {
            return ResponseEntity.badRequest().build();
        }

        LeituraSensor leitura = new LeituraSensor();
        leitura.setValor(dto.valor());
        leitura.setInterpretacao(dto.interpretacao());
        leitura.setSensor(sensor);
        leitura.setAnalise(analise);

        LeituraSensor salva = leituraService.salvar(leitura);

        return ResponseEntity.status(201).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeituraSensor> atualizar(@PathVariable Long id, @Valid @RequestBody LeituraSensorDTO dto) {
        LeituraSensor existente = leituraService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        Sensor sensor = sensorService.buscarPorId(dto.sensorId());
        Analise analise = analiseService.buscarPorId(dto.analiseId());

        if (sensor == null || analise == null) {
            return ResponseEntity.badRequest().build();
        }

        existente.setValor(dto.valor());
        existente.setInterpretacao(dto.interpretacao());
        existente.setSensor(sensor);
        existente.setAnalise(analise);

        LeituraSensor atualizada = leituraService.salvar(existente);

        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        LeituraSensor existente = leituraService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        leituraService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}