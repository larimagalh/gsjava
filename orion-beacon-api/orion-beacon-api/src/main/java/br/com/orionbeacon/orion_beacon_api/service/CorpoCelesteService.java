package br.com.orionbeacon.orion_beacon_api.service;

import br.com.orionbeacon.orion_beacon_api.entity.CorpoCeleste;
import br.com.orionbeacon.orion_beacon_api.repository.CorpoCelesteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorpoCelesteService {

    private final CorpoCelesteRepository repository;

    public CorpoCelesteService(CorpoCelesteRepository repository) {
        this.repository = repository;
    }

    public List<CorpoCeleste> listarTodos() {
        return repository.findAll();
    }

    public CorpoCeleste buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CorpoCeleste salvar(CorpoCeleste corpoCeleste) {
        return repository.save(corpoCeleste);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}