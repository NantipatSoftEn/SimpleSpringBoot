package com.example.simplespringboot.business;

import com.example.simplespringboot.exception.BaseException;
import com.example.simplespringboot.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusiness {
    public  String getProductById(String id) throws BaseException {
        if(Objects.equals("1234",id)){
            throw ProductException.NotFound();
        }
        return id;
    }

}
