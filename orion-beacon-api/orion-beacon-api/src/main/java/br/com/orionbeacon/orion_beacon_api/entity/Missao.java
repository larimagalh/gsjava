package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_MISSAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String status;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaAnalisada area;
}
