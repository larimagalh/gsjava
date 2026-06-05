package br.com.orionbeacon.orion_beacon_api.service;

import br.com.orionbeacon.orion_beacon_api.entity.Analise;
import br.com.orionbeacon.orion_beacon_api.repository.AnaliseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseService {

    private final AnaliseRepository repository;

    public AnaliseService(AnaliseRepository repository) {
        this.repository = repository;
    }

    public List<Analise> listarTodos() {
        return repository.findAll();
    }

    public Analise buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Analise salvar(Analise analise) {
        return repository.save(analise);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}