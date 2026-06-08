package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_MISSAO_SENSOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissaoSensor {

    @EmbeddedId
    private MissaoSensorId id;

    @ManyToOne
    @MapsId("missaoId")
    @JoinColumn(name = "MISSAO_ID")
    private Missao missao;

    @ManyToOne
    @MapsId("sensorId")
    @JoinColumn(name = "SENSOR_ID")
    private Sensor sensor;

    @Column(name = "DS_FUNCAO_SENSOR")
    private String funcaoSensor;
}