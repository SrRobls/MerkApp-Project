package com.merkapp.merkapp.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.merkapp.merkapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private com.merkapp.merkapp.model.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        log.info("Dentro de loadUserByUsername {}", correo);

        userDetail = userRepository.findByEmail(correo);
        log.info("User {}", userDetail);
        if (!Objects.isNull(userDetail)) {
            return User.withUsername(correo)
                    // Cuando consulten de base de datos no encripten dos veces
                    .password(userDetail.getPassword())
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    public com.merkapp.merkapp.model.User getUserDetail() {
        return userDetail;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
