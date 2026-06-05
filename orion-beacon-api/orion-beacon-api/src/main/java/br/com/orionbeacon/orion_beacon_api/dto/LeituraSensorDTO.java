package br.com.orionbeacon.orion_beacon_api.dto;

public record LeituraSensorDTO(
        Long id,
        Double valor,
        String unidade,
        Long sensorId,
        Long analiseId
) {
}