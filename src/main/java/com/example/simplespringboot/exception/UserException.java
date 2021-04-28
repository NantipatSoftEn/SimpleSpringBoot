package com.example.simplespringboot.exception;

public class UserException extends BaseException {
     UserException(String code){
        super("user." + code);
    }

    public  static  UserException requestNull(){
        return new UserException("register.req.null");
    }

    public  static  UserException emailNull(){
        return new UserException("register.email.null");
    }

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


}
