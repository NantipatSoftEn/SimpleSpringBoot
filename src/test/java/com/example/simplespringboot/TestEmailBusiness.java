package com.example.simplespringboot;

import com.example.simplespringboot.business.EmailBusiness;
import com.example.simplespringboot.entity.Address;
import com.example.simplespringboot.entity.Social;
import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.BaseException;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.service.AddressService;
import com.example.simplespringboot.service.SocialService;
import com.example.simplespringboot.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEmailBusiness {

    @Autowired
    private EmailBusiness emailBusiness;


    @Order(1)
    @Test
    void testSendActivateEmail() throws BaseException {
        emailBusiness.sendActivateUserEmail(TestData.email,TestData.name,TestData.token);
    }
    interface  TestData {
        String email ="keymasterviriya1150@gmail.com";
        String name = "Army";
        String token ="SDOFKDSLFSFKDSFK32#3";
    }

}
