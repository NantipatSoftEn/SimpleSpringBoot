package com.example.simplespringboot.repository;

import com.example.simplespringboot.entity.Social;
import com.example.simplespringboot.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social,String> {
    Optional<Social> findByUser(User user);
}
