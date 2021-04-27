package com.example.simplespringboot.business;

import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.model.MRegisterRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TestBusiness {
    public  String register(MRegisterRequest request) throws UserException {
        if(request == null){
            throw UserException.requestNull();
        }

        if(request.getEmail()==null){
            throw UserException.requestNull();
        }
        return request.getName() ;
    }
}
