package br.com.orionbeacon.orion_beacon_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_CORPO_CELESTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CorpoCeleste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;
}
