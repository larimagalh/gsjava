package br.com.orionbeacon.orion_beacon_api.service;

import br.com.orionbeacon.orion_beacon_api.entity.Missao;
import br.com.orionbeacon.orion_beacon_api.repository.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissaoService {

    private final MissaoRepository repository;

    public MissaoService(MissaoRepository repository) {
        this.repository = repository;
    }

    public List<Missao> listarTodos() {
        return repository.findAll();
    }

    public Missao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Missao salvar(Missao missao) {
        return repository.save(missao);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}