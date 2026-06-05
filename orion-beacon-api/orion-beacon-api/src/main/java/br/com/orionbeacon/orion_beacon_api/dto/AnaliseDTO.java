package br.com.orionbeacon.orion_beacon_api.dto;

import java.time.LocalDate;

public record AnaliseDTO(
        Long id,
        String classificacao,
        String observacoes,
        LocalDate dataAnalise,
        Long areaId
) {
}