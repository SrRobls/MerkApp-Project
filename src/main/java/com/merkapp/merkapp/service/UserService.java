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

    public ResponseEntity<ResponseNewUser> createUser(
            CreateUserDTO userDTO) {
        log.info("Creación de usuario: {} ", userDTO);
        //Recordemos que los reposritory sirve para interactuar con la entidad en la base de datos y usamos los DTOs para mejor manejor de atributos de estos
        User user = userRepository.findByEmail(userDTO.email());

        if (user != null) {
            throw new MerkAppApiException(HttpStatus.CONFLICT,
                    Error.USER_EXIST);
        }

        User newUser = new User(
                userDTO.userName(),
                userDTO.email(),
                passwordEncoder().encode(userDTO.password()),
                userDTO.authorities());

        log.info("Nuevo user: {}", newUser);
        //Creamos el usuario en dado caso de que todo este bien (por ejemplo; que no haya usuario con el mismo email) y retornamos sus credenciales con la
        // contraseña encriptada.
        userRepository.save(newUser);

        return ResponseEntity.ok(convertUserToDTO(newUser));
    }

    //Usamos este metodo para enviar la respuesta al cliente con las credenciales de usuario creado
    private ResponseNewUser convertUserToDTO(User user) {
        return new ResponseNewUser(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getAuthorities());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
