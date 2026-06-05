package br.com.orionbeacon.orion_beacon_api.repository;

import br.com.orionbeacon.orion_beacon_api.entity.Missao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissaoRepository extends JpaRepository<Missao, Long> {
}