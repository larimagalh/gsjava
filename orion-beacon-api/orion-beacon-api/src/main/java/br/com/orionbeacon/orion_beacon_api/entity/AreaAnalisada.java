package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_AREA_ANALISADA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaAnalisada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME_AREA")
    private String nome_area;

    private String regiao;

    @Embedded
    private Coordenada coordenada;

    @ManyToOne
    @JoinColumn(name = "corpo_celeste_id")
    private CorpoCeleste corpoCeleste;
}
