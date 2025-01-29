package com.merkapp.merkapp.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
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

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Map<String, String> login(AuthRequestDTO authRequestDto) {
        log.info("Ingresando al login");
        final var authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.email(), authRequestDto.password()));
        final var userDetails = (UserDetails) authenticate.getPrincipal();
        return getToken(userDetails);
    }

    public Map<String, String> getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final var username = userDetails.getUsername();
        final var token = jwtService.createToken(Map.of("role", roles), username);
        return Map.of("token", token);
    }

}
