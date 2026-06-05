package br.com.orionbeacon.orion_beacon_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Orion Beacon API funcionando!";
    }
}
