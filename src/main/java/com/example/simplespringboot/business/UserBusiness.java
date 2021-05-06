package com.example.simplespringboot.business;

import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.FileException;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.mapper.UserMapper;
import com.example.simplespringboot.model.MLoginRequest;
import com.example.simplespringboot.model.MLoginResponse;
import com.example.simplespringboot.model.MRegisterRequest;
import com.example.simplespringboot.model.MRegisterResponse;

import com.example.simplespringboot.service.TokenService;
import com.example.simplespringboot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Security;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {
    private  final UserService  userService;
    private  final UserMapper userMapper;
    private  final TokenService tokenService;
    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    public String login(MLoginRequest request) throws UserException {

        MLoginResponse m = new MLoginResponse();
        Optional<User>  opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw UserException.loginFailEmailNotFound();
        }


        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            throw UserException.loginFailPasswordIncorrect();
        }

        return tokenService.tokenize(user);


    }

    public String refreshToken() throws UserException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();
        Optional<User> opt =  userService.findById(userId);
        if(opt.isEmpty()){
            throw  UserException.notFound();
        }
        User user = opt.get();
        return tokenService.tokenize(user);
    }

    public MRegisterResponse register(MRegisterRequest request) throws UserException {
        User user =  userService.create(request.getEmail(),request.getPassword(),request.getName());
         return userMapper.toRegisTerResponse(user);


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
