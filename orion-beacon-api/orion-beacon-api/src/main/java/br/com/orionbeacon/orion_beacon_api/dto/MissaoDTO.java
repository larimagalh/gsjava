package br.com.orionbeacon.orion_beacon_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MissaoDTO(
        Long id,

        @NotBlank(message = "O nome da missão é obrigatório")
        String nome,

        @NotBlank(message = "O status da missão é obrigatório")
        String status,

        @NotNull(message = "O ID da área analisada é obrigatório")
        Long areaId
) {
}