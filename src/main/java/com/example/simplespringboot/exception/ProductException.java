package com.example.simplespringboot.exception;

public class ProductException extends  BaseException{
    public ProductException(String code){
        super("product."+code);
    }
    public static  ProductException NotFound(){
        return new ProductException("not.found");

    }
}
