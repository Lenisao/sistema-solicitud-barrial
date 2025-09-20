package com.prototipo.ApiPrototipo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    public String saludar() {
        return "¡Hola desde tu API Spring Boot!";
    }
}

