package com.example.simplespringboot.service;
import com.example.simplespringboot.entity.Address;
import com.example.simplespringboot.entity.User;;
import com.example.simplespringboot.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;


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
