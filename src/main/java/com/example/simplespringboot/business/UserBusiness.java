package com.example.simplespringboot.business;

import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.EmailException;
import com.example.simplespringboot.exception.FileException;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.mapper.UserMapper;
import com.example.simplespringboot.model.*;
import com.example.simplespringboot.service.TokenService;
import com.example.simplespringboot.service.UserService;
import com.example.simplespringboot.util.SecurityUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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

    public MUserProfile getMyUserProfile() throws UserException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }

        return userMapper.toUserProfile(optUser.get());
    }

    public MUserProfile updateMyUserProfile(MUpdateUserProfileRequest request) throws UserException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();

        // validate
        if (ObjectUtils.isEmpty(request.getName())) {
            throw UserException.updateNameNull();
        }

        User user = userService.updateName(userId, request.getName());

        return userMapper.toUserProfile(user);
    }

    public MLoginResponse login(MLoginRequest request) throws UserException {

        MLoginResponse m = new MLoginResponse();
        Optional<User>  opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            throw UserException.loginFailEmailNotFound();
        }

        // verify password
        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            throw UserException.loginFailPasswordIncorrect();
        }

        // validate activate status
        if(!user.isActivated()){
            throw UserException.loginFailUserUnactivated();
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
        System.out.println("user"+user);
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

    public MActivateResponse activate(MActivateRequest request) throws  UserException {
        String token = request.getToken();
        if (StringUtil.isNullOrEmpty(token)) {
            throw UserException.activateNoToken();
        }

        Optional<User> opt = userService.findByToken(token);
        if (opt.isEmpty()) {
            throw UserException.activateFail();
        }

        User user = opt.get();
        // activate แล้ว (กด activate  ครั้งที่ 2)
        if (user.isActivated()) {
            throw UserException.activateAlready();
        }


        Date now = new Date();
        Date expireDate = user.getTokenExpire();
        if (now.after(expireDate)) {
            throw UserException.activateTokenExpire();
        }

        user.setActivated(true);
        userService.update(user);

        MActivateResponse response = new MActivateResponse();
        response.setSuccess(true);
        return response;
    }


    public void resendActivationEmail(MResendActivationEmailRequest request) throws UserException, EmailException {
        String token = request.getToken();
        System.out.println("token:"+token);
        if (StringUtil.isNullOrEmpty(token)) {
            throw UserException.resendActivationEmailNoToken();
        }

        Optional<User> opt = userService.findByToken(token);
        if (opt.isEmpty()) {
            throw UserException.resendActivationTokenNotFound();
        }

        User user = opt.get();

        if (user.isActivated()) {
            throw UserException.activateAlready();
        }

        user.setToken(SecurityUtil.generateToken());
        user.setTokenExpire(nextXMinute(30));
        user = userService.update(user);

        sendEmail(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userService.getAllUsers();
    }

    private Date nextXMinute(int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,minute);
        return calendar.getTime();
    }


}
