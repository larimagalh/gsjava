package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_ANALISE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classificacao;

    private String observacao;

    private LocalDate dataAnalise;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaAnalisada area;
}
