package com.merkapp.merkapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.merkapp.merkapp.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
