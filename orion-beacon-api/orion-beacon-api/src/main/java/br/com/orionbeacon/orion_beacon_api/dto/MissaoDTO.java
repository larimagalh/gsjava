package br.com.orionbeacon.orion_beacon_api.dto;

public record MissaoDTO(
        Long id,
        String nome,
        String status,
        Long areaId
) {
}