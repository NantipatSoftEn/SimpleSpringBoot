package com.example.simplespringboot.service;

import com.example.simplespringboot.entity.Address;
import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.repository.AddressRepository;
import com.example.simplespringboot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressService {
    private  final AddressRepository repository;


    public AddressService(AddressRepository repository){
        this.repository =  repository;
    }
    public List<Address> findByUser(User user){
        return  repository.findByUser(user);
    }

    public Address create(User user,String zipcode){
        Address entity = new Address();
        entity.setUser(user);
        entity.setZipcode(zipcode);
        return repository.save(entity);
    }

}
