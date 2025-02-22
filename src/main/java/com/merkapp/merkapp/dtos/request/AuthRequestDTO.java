package com.merkapp.merkapp.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;

//Usamos Record que es una forma más rapida para crear los constructores y setters y getters, etc de las clases
@Schema
public record AuthRequestDTO(
        //Introduccimos el decorador @Schema que marca la clase como documentación de swagger, ademas con example lo que hacemos
        //es proporcionar ejemplos en la respectiva doucmentación
        @Schema(example = "admin@example.com", description = "this filed  use to pass email") String email,
        @Schema(example = "admin", description = "this filed  use to pass password") String password) {

}
