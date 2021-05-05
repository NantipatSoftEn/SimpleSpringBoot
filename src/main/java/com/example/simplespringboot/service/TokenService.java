package com.example.simplespringboot.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.simplespringboot.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issure}")
    private String issure;

    public  String tokenize(User user){
        Algorithm algorithm  = Algorithm.HMAC256(secret);
        return JWT.create().withIssuer(issure).
                withClaim("principal",user.getId()).
                withClaim("role","USER").
                sign(algorithm);


    }
}
