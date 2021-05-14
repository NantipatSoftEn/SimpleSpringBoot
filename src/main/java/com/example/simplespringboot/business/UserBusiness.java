package com.example.simplespringboot.business;

import com.example.common.EmailRequest;
import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.EmailException;
import com.example.simplespringboot.exception.FileException;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.mapper.UserMapper;
import com.example.simplespringboot.model.MLoginRequest;
import com.example.simplespringboot.model.MLoginResponse;
import com.example.simplespringboot.model.MRegisterRequest;
import com.example.simplespringboot.model.MRegisterResponse;
import com.example.simplespringboot.service.TokenService;
import com.example.simplespringboot.service.UserService;
import com.example.simplespringboot.util.SecurityUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Log4j2
public class UserBusiness {
    private  final UserService  userService;
    private  final UserMapper userMapper;
    private  final TokenService tokenService;
    private  final EmailBusiness emailBusiness;
    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService, EmailBusiness emailBusiness) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.emailBusiness = emailBusiness;
    }

    public MLoginResponse login(MLoginRequest request) throws UserException {

        MLoginResponse m = new MLoginResponse();
        Optional<User>  opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw UserException.loginFailEmailNotFound();
        }


        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            throw UserException.loginFailPasswordIncorrect();
        }
        MLoginResponse response= new MLoginResponse();
        response.setToken(tokenService.tokenize(user));
        return response;

    }

    public String refreshToken() throws UserException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()){
            throw UserException.unauthorized();
        }
        String userId = opt.get();
        Optional<User> optUser =  userService.findById(userId);
        if(optUser.isEmpty()){
            throw  UserException.notFound();
        }
        User user = optUser.get();
        return tokenService.tokenize(user);
    }
    private void sendEmail(User user) throws EmailException {
        // TODO: generate token
        String token  = user.getToken();
        emailBusiness.sendActivateUserEmail(user.getEmail(),user.getName(),token);


    }
    public MRegisterResponse register(MRegisterRequest request) throws UserException, EmailException {
        String token =SecurityUtil.generateToken();
        User user =  userService.create(request.getEmail(),request.getPassword(),request.getName(),token,nextXMinute(30));
        sendEmail(user);
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

    private Date nextXMinute(int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,minute);
        return calendar.getTime();
    }


}
