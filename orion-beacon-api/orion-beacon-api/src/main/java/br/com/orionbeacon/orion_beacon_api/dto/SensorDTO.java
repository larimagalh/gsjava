package br.com.orionbeacon.orion_beacon_api.dto;

public record SensorDTO(
        Long id,
        String nome,
        String tipo,
        String descricao
) {
}