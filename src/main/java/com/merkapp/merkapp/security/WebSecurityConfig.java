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

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors() // Asegura que CORS esté habilitado
                .and()
                .csrf().disable() // Deshabilita CSRF ya que estás usando JWT
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/stores/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/signup/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/stores/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/stores/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/stores/**").authenticated()
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
