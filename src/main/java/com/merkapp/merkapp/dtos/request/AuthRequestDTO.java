package com.merkapp.merkapp.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record AuthRequestDTO(
        @Schema(example = "admin@example.com", description = "this filed  use to pass email") String email,
        @Schema(example = "admin", description = "this filed  use to pass password") String password) {

}
