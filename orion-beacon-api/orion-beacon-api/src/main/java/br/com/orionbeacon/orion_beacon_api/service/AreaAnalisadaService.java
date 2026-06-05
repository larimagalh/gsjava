package br.com.orionbeacon.orion_beacon_api.service;

import br.com.orionbeacon.orion_beacon_api.entity.AreaAnalisada;
import br.com.orionbeacon.orion_beacon_api.repository.AreaAnalisadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaAnalisadaService {

    private final AreaAnalisadaRepository repository;

    public AreaAnalisadaService(AreaAnalisadaRepository repository) {
        this.repository = repository;
    }

    public List<AreaAnalisada> listarTodos() {
        return repository.findAll();
    }

    public AreaAnalisada buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public AreaAnalisada salvar(AreaAnalisada area) {
        return repository.save(area);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}