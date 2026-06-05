package br.com.orionbeacon.orion_beacon_api.service;

import br.com.orionbeacon.orion_beacon_api.entity.Sensor;
import br.com.orionbeacon.orion_beacon_api.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository repository;

    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public List<Sensor> listarTodos() {
        return repository.findAll();
    }

    public Sensor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Sensor salvar(Sensor sensor) {
        return repository.save(sensor);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}