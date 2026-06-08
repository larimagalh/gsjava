package br.com.orionbeacon.orion_beacon_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AnaliseDTO(
        Long id,

        @NotBlank(message = "A classificação da análise é obrigatória")
        String classificacao,

        @NotBlank(message = "As observações da análise são obrigatórias")
        String observacoes,

        @NotNull(message = "A data da análise é obrigatória")
        LocalDate dataAnalise,

        @NotNull(message = "O ID da área analisada é obrigatório")
        Long areaId
) {
}