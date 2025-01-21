package com.merkapp.merkapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merkapp.merkapp.dtos.request.AuthRequestDTO;
import com.merkapp.merkapp.dtos.request.CreateUserDTO;
import com.merkapp.merkapp.dtos.response.ResponseNewUser;
import com.merkapp.merkapp.exception.MerkAppApiException;
import com.merkapp.merkapp.service.AuthService;
import com.merkapp.merkapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    public AuthService authService;

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authRequest(@RequestBody AuthRequestDTO authRequestDto) {
        log.info("AuthResource.authRequest start {}", authRequestDto);
        var userRegistrationResponse = authService.login(authRequestDto);
        log.info("AuthResource.authRequest end {}", userRegistrationResponse);
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/signup")
    public @ResponseBody ResponseEntity<ResponseNewUser> addNewUser(@RequestBody CreateUserDTO userData) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            return userService.createUser(userData);
        } catch (IllegalArgumentException e) {
            throw new MerkAppApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
