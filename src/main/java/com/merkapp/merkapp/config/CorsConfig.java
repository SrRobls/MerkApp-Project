package com.merkapp.merkapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;


//En esta configuración nos encargamos quienes pueden hacer solicitudes al servidor springboot. EN este caso el unico que puede es el dominio respectuvo
//para Flutter
@Configuration
@EnableWebMvc
@Slf4j
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Ingresando a los CORS");
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Usa esto en lugar de allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Access-Control-Allow-Origin")
                .allowCredentials(true);
    }
}


