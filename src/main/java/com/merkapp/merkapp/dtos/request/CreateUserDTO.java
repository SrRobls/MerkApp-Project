package com.merkapp.merkapp.dtos.request;

import java.util.Set;

import com.merkapp.merkapp.enums.Role;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record CreateUserDTO(
                @Schema(example = "admin", description = "this filed use to pass username") String userName,
                @Schema(example = "admin@example.com", description = "this filed use to pass email") String email,
                @Schema(example = "ADMIN", description = "this filed use to pass authorities") Set<Role> authorities,
                @Schema(example = "admin", description = "this filed use to pass password") String password) {

}
