package com.merkapp.merkapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Agregar orígenes específicos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permitir todos los métodos necesarios
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Origin")
                .allowCredentials(true); // Asegúrate de que las credenciales son permitidas
        // Opcional, para caché de las configuraciones de CORS
    }
}
