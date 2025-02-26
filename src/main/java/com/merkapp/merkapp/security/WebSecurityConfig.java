package com.merkapp.merkapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthFilter jwtAuthFilter;

    private static final String[] PUBLIC_GET_URLS = {
            "/api/v1/products/**",
            "/api/v1/stores/**",
            "/swagger-ui/**",
            "/api/v1/api-docs/**",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources",
            "/api/v1/user/**"
    };


    private static final String[] PROTECTED_URLS = {
            "/api/v1/products/**",
            "/api/v1/stores/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso público para consultas GET
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/stores/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/signup/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()


                        // Requerir token para crear, editar o eliminar productos
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").authenticated()

                        // Requerir token para crear, editar o eliminar tiendas
                        .requestMatchers(HttpMethod.POST, "/api/v1/stores/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/stores/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/stores/**").authenticated()

                        // Otras rutas requerirán autenticación
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .build();
    }



    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
