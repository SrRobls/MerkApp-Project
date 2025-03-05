package com.merkapp.merkapp.service;

import java.util.Map;

import com.merkapp.merkapp.dtos.response.AuthResponseDTO;
import com.merkapp.merkapp.exception.MerkAppApiException;
import com.merkapp.merkapp.security.AuthDetailsService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.merkapp.merkapp.dtos.request.AuthRequestDTO;
import com.merkapp.merkapp.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    // Gracias @RequiredArgsConstructor lombok nos genera el contructor de estos
    // modulos y asi poderlo inyectar acá
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthDetailsService authDetailsService;

    public AuthResponseDTO login(AuthRequestDTO authRequestDto) {
        log.info("Ingresando al login");
        // Autenticamos al usuario
        try {
            final var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDto.email(), authRequestDto.password()));
            final var userDetails = (UserDetails) authentication.getPrincipal();

            final var token = jwtService.createToken(Map.of("role", userDetails.getAuthorities()),
                    userDetails.getUsername());

            var user = authDetailsService.getUserDetail();

            return new AuthResponseDTO(token, user.getId(), user.getUsername(), user.getEmail(), user.getAuthorities());
        } catch (BadCredentialsException e) {
            // TODO: handle exception
            log.info("Error de credenciales");
            throw new MerkAppApiException(HttpStatus.CONFLICT, "Credenciales inválidas");
        }
    }

    public Map<String, String> getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final var username = userDetails.getUsername();
        // Creamos un token con base la iformación del usuario y el rol de este
        final var token = jwtService.createToken(Map.of("role", roles), username);
        return Map.of("token", token);
    }

}
