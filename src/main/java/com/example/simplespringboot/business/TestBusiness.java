package com.example.simplespringboot.business;

import com.example.simplespringboot.model.MRegisterRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TestBusiness {
    public  String register(MRegisterRequest request) throws IOException {
        if(request == null){
            throw  new IOException("null.request");
        }

        if(request.getEmail()==null){
            throw  new IOException("null.email");
        }
        return request.getName() ;
    }
}
