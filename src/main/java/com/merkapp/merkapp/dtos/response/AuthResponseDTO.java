package com.merkapp.merkapp.dtos.response;

import java.util.Set;
import com.merkapp.merkapp.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Respuesta del Login que incluye el token y la información del usuario")
public record AuthResponseDTO(
        @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", description = "JWT Token") String token,
        @Schema(example = "1", description = "ID del usuario") Long id,
        @Schema(example = "admin", description = "Nombre de usuario") String userName,
        @Schema(example = "admin@example.com", description = "Email del usuario") String email,
        @Schema(description = "Roles del usuario") Set<Role> authorities
) {}
