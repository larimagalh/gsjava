package br.com.orionbeacon.orion_beacon_api.dto;

public record LoginRequest(
        String username,
        String password
) {}