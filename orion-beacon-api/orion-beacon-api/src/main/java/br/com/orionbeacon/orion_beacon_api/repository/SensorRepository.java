package br.com.orionbeacon.orion_beacon_api.repository;

import br.com.orionbeacon.orion_beacon_api.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}