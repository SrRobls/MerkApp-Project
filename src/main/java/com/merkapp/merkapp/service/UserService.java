package com.merkapp.merkapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merkapp.merkapp.constanst.Error;
import com.merkapp.merkapp.dtos.request.CreateUserDTO;
import com.merkapp.merkapp.dtos.response.ResponseNewUser;
import com.merkapp.merkapp.exception.MerkAppApiException;
import com.merkapp.merkapp.model.User;
import com.merkapp.merkapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<ResponseNewUser> createUser(CreateUserDTO userDTO) {
        log.info("Creación de usuario: {} ", userDTO);

        User user = userRepository.findByEmail(userDTO.email());

        if (user != null) {
            throw new MerkAppApiException(HttpStatus.CONFLICT, Error.USER_EXIST);
        }

        User newUser = new User(
                userDTO.userName(),
                userDTO.email(),
                passwordEncoder.encode(userDTO.password()), // Ahora usa la instancia inyectada
                userDTO.authorities());

        log.info("Nuevo user: {}", newUser);

        userRepository.save(newUser);

        return ResponseEntity.ok(convertUserToDTO(newUser));
    }

    //Usamos este metodo para enviar la respuesta al cliente con las credenciales de usuario creado
    private ResponseNewUser convertUserToDTO(User user) {
        return new ResponseNewUser(
                user.getId(),
                user.getUsername(), // hora devuelve el username correcto
                user.getEmail(), //Ahora devuelve el email correcto
                user.getAuthorities()
        );
    }


    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
