package br.com.orionbeacon.orion_beacon_api.repository;

import br.com.orionbeacon.orion_beacon_api.entity.MissaoSensor;
import br.com.orionbeacon.orion_beacon_api.entity.MissaoSensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissaoSensorRepository extends JpaRepository<MissaoSensor, MissaoSensorId> {
}
