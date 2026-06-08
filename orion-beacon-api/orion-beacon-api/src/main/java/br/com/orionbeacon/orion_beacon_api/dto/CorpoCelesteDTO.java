package br.com.orionbeacon.orion_beacon_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CorpoCelesteDTO(
        Long id,

        @NotBlank(message = "O nome do corpo celeste é obrigatório")
        String nome
) {
}