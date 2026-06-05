package br.com.orionbeacon.orion_beacon_api.service;

import br.com.orionbeacon.orion_beacon_api.entity.LeituraSensor;
import br.com.orionbeacon.orion_beacon_api.repository.LeituraSensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraSensorService {

    private final LeituraSensorRepository repository;

    public LeituraSensorService(LeituraSensorRepository repository) {
        this.repository = repository;
    }

    public List<LeituraSensor> listarTodos() {
        return repository.findAll();
    }

    public LeituraSensor buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LeituraSensor salvar(LeituraSensor leitura) {
        return repository.save(leitura);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}