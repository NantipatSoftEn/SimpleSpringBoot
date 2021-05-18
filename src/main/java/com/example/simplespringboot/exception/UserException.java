package com.example.simplespringboot.exception;

import com.example.simplespringboot.entity.User;

public class UserException extends BaseException {
     UserException(String code){
        super("user." + code);
    }

    public  static  UserException requestNull(){
        return new UserException("register.req.null");
    }

    public static UserException notFound(){
        return new UserException("login.not.found");
    }

    public static UserException unauthorized(){
        return new UserException("unauthorized");
    }

    public  static  UserException emailNull(){
        return new UserException("register.email.null");
    }
    // CREATE
    public static  UserException createEmailNull(){
         return new UserException("create.email.null");
    }

    public  static  UserException createEmailDuplicated(){
        return  new UserException("create.email.duplicated");
    }

    public static  UserException createPasswordNull(){
        return new UserException("create.password.null");
    }

    public static  UserException createNameNull(){
        return new UserException("create.name.null");
    }
    // LOGIN
    public static UserException loginFailEmailNotFound(){
         return new UserException("login.fail.email");
    }

    public static UserException loginFailPasswordIncorrect(){
         return new UserException("login.fail.password");
    }

    public static UserException loginFailUserUnactivated() {
        return new UserException("login.fail.unactivated");
    }

    // ACTIVATE

    public static UserException activateNoToken() {
        return new UserException("activate.no.token");
    }

    public static UserException activateAlready() {
        return new UserException("activate.already");
    }

    public static UserException activateFail() {
        return new UserException("activate.fail");
    }

    public static UserException activateTokenExpire() {
        return new UserException("activate.token.expire");
    }

    // RESEND ACTIVATION EMAIL

    public static UserException resendActivationEmailNoToken() {
        return new UserException("resend.activation.no.token");
    }

    public static UserException resendActivationTokenNotFound() {
        return new UserException("resend.activation.fail");
    }

    // UPDATE

    public static UserException updateNameNull() {
        return new UserException("update.name.null");
    }
}
