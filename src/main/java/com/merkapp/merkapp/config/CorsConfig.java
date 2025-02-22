package com.merkapp.merkapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;


//En esta configuración nos encargamos quienes pueden hacer solicitudes al servidor springboot. EN este caso el unico que puede es el dominio respectuvo
//para Flutter
@Configuration //Lo usamos para definir configuraciones del servidor springboot, en este caso para definir el comportaminetos de los cors
@EnableWebMvc
@Slf4j //Para mostrar por consola mensajes
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info(("Ingresando a los COSR"));
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*") // Permitir cualquier encabezado
                .exposedHeaders("Access-Control-Allow-Origin") // Exponer el encabezado CORS
                .allowCredentials(true); //En esta configuración hacemos que se haga la autenticación con tokens (en este caso JWT)
    }

}
