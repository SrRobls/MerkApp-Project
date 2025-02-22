package com.merkapp.merkapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.merkapp.merkapp.security.jwt.JwtAuthFilter;

@Configuration
public class WebSecurityConfig {

    //Esta clase configura la aplicación para que suae la configuración con jwt

    @Autowired
    public UserDetailsService userDetailsService;

    //Modulo para verificar que se recibe el token en los headers
    @Autowired
    public JwtAuthFilter jwtAuthFilter;

    // Reduced whitelist for simplicity
    //Definirmos en que partes de la aplicación no es necesario la autenticación
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/swagger-ui/**",
            "/api/v1/api-docs/**",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //Desactivamos los Cors y CSFR
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                //Implementamos para que permita a lista de WHITE_LIST_URL sin autenticación
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URL)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                //quitamos las sesiones STATELES para que en cada petición se deba mandar el token
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .build();

    }

    @Bean
    //Se usa para autenticar a los usuarios en el servicio de autenticación
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
