package com.example.simplespringboot.repository;

import com.example.simplespringboot.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// for action entity
public interface UserRepository extends CrudRepository<User,String> {

    Optional<User> findByEmail(String s);
    boolean existsByEmail(String email);

}
