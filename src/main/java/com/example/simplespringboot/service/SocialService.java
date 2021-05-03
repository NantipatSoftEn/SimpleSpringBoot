package com.example.simplespringboot.service;

import com.example.simplespringboot.entity.Social;
import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.repository.SocialRepository;
import com.example.simplespringboot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SocialService {
    private  final SocialRepository repository;


    public SocialService(SocialRepository repository){
        this.repository =  repository;
    }

    public Optional<Social> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Social create(User user,String facebook,String instragram){
        Social entity= new Social();
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setInstagram(instragram);
        return repository.save(entity);
    }
}
