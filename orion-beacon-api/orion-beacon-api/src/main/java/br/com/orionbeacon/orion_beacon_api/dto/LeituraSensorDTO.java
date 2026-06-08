package br.com.orionbeacon.orion_beacon_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LeituraSensorDTO(
        Long id,

        @NotNull(message = "O valor da leitura é obrigatório")
        Double valor,

        @NotBlank(message = "A interpretação da leitura é obrigatória")
        String interpretacao,

        @NotNull(message = "O ID do sensor é obrigatório")
        Long sensorId,

        @NotNull(message = "O ID da análise é obrigatório")
        Long analiseId
) {
}