package com.merkapp.merkapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {
    @GetMapping("/saludo")
    public String saludo() {
        return "Hola, este es nuestro proyecto spring boot para desarrollo movil! chajaja";
    }
}
