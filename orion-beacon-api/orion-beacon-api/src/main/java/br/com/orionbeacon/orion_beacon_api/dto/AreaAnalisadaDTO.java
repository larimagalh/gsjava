package br.com.orionbeacon.orion_beacon_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AreaAnalisadaDTO(
        Long id,

        @NotBlank(message = "O nome da área é obrigatório")
        String nome,

        @NotBlank(message = "A região é obrigatória")
        String regiao,

        @NotNull(message = "A latitude é obrigatória")
        Double latitude,

        @NotNull(message = "A longitude é obrigatória")
        Double longitude,

        @NotNull(message = "O ID do corpo celeste é obrigatório")
        Long corpoCelesteId
) {
}