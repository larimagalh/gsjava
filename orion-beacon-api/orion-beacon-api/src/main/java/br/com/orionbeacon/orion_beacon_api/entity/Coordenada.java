package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coordenada {

    private Double latitude;
    private Double longitude;
}