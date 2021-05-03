package com.example.simplespringboot.repository;

import com.example.simplespringboot.entity.Address;
import com.example.simplespringboot.entity.Social;
import com.example.simplespringboot.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address,String> {
    List<Address> findByUser(User user);
}
