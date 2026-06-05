package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_LEITURA_SENSOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private String interpretacao;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "analise_id")
    private Analise analise;
}
