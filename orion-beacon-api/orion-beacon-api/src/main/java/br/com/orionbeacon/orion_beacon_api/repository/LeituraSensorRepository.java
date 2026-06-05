package br.com.orionbeacon.orion_beacon_api.repository;

import br.com.orionbeacon.orion_beacon_api.entity.LeituraSensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, Long> {
}