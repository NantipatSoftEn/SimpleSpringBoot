package com.example.simplespringboot.business;

import com.example.simplespringboot.exception.FileException;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.model.MRegisterRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

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

    public  String uploadProfilePicture(MultipartFile file) throws FileException {
        if(file == null){
            throw FileException.fileNull();
        }
        if(file.getSize() > 1048576 * 2) {
            throw  FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if(contentType == null){
            throw FileException.fileNull();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg","image/png");
        if(!supportedTypes.contains(contentType)){
            throw FileException.unsupported();
        }
        try{
            byte[] bytes= file.getBytes();

        } catch (IOException e){
            e.printStackTrace();
        }
        return "" + file.getSize();
    }
}
