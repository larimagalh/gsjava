package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorRadar extends Sensor {

    private Double profundidade;
}
