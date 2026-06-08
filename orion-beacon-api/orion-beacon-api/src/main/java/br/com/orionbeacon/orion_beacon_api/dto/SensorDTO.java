package br.com.orionbeacon.orion_beacon_api.dto;

import jakarta.validation.constraints.NotBlank;

public record SensorDTO(
        Long id,

        @NotBlank(message = "O nome do sensor é obrigatório")
        String nome,

        @NotBlank(message = "O tipo do sensor é obrigatório")
        String tipo,

        @NotBlank(message = "A descrição do sensor é obrigatória")
        String descricao
) {
}