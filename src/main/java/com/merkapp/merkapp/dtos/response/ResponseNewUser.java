package com.merkapp.merkapp.dtos.response;

import java.util.Set;

import com.merkapp.merkapp.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record ResponseNewUser(
                // Se usa para manejar las respuestas de los usuarios creados
                @Schema(example = "admin", description = "this filed  use to pass username") Long id,
                @Schema(example = "admin", description = "this filed  use to pass userName") String userName,
                @Schema(example = "admin@example.com", description = "this filed  use to pass email") String email,
                @Schema(example = "2f23f293f8j23poif2u", description = "this filed  use to the token") String token,
                @Schema(example = "[ADMIN]", description = "this filed  use to pass authorities") Set<Role> authorities) {

}
