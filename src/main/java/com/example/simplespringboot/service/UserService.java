package com.example.simplespringboot.service;

import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private  final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public  UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository =  repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean matchPassword(String rawPassword,String encodedPassword){
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }

    public User create(String email, String password, String name) throws UserException {

        // validate

        if(Objects.isNull(email)){
            throw UserException.createEmailNull();
        }

        if(Objects.isNull(password)){
            throw UserException.createPasswordNull();
        }

        if(Objects.isNull(name)){
            throw UserException.createNameNull();
        }
        // write for make sure
        if(repository.existsByEmail(email)){
            throw  UserException.createEmailDuplicated();
        }

        User entity =  new User();
        entity.setEmail(email);
        entity.setPassword(password);
        entity.setName(name);
        return repository.save(entity);
    }
}
