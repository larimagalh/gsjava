package br.com.orionbeacon.orion_beacon_api.controller;

import br.com.orionbeacon.orion_beacon_api.dto.LoginRequest;
import br.com.orionbeacon.orion_beacon_api.dto.LoginResponse;
import br.com.orionbeacon.orion_beacon_api.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        if ("admin".equals(request.username())
                && "123456".equals(request.password())) {

            String token =
                    jwtService.generateToken(request.username());

            return new LoginResponse(token);
        }

        throw new RuntimeException("Usuário ou senha inválidos");
    }
}