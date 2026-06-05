package br.com.orionbeacon.orion_beacon_api.dto;

public record AreaAnalisadaDTO(
        Long id,
        String nome,
        String regiao,
        Double latitude,
        Double longitude,
        Long corpoCelesteId
) {
}