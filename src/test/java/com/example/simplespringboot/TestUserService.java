package com.example.simplespringboot;

import com.example.simplespringboot.entity.Address;
import com.example.simplespringboot.entity.Social;
import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.service.AddressService;
import com.example.simplespringboot.service.SocialService;
import com.example.simplespringboot.service.UserService;
import com.example.simplespringboot.util.SecurityUtil;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private SocialService socialService;
    @Autowired
    private AddressService addressService;

    @Order(1)
    @Test
    void testCreate() throws  UserException {
        String token = SecurityUtil.generateToken();

        User user = userService.create(TestCreateData.email,TestCreateData.password,TestCreateData.name,token,nextXMinute(30));
        // check not null
        System.out.println(user);
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        Assertions.assertEquals(TestCreateData.email,user.getEmail());
        boolean isMatched =userService.matchPassword(TestCreateData.password,user.getPassword());
        System.out.println(isMatched);
        Assertions.assertTrue(isMatched);


        Assertions.assertEquals(TestCreateData.name,user.getName());
    }

    private Date nextXMinute(int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,minute);
        return calendar.getTime();
    }
    @Order(2)
    @Test
    void testUpdate() throws UserException {
    Optional<User> opt = userService.findByEmail(TestCreateData.email);
    Assertions.assertTrue(opt.isPresent());

    User user = opt.get();
    User updateUser = userService.updateName(user.getId(),TestUpdateData.name);
    Assertions.assertNotNull(updateUser);

    }

    @Order(3)
    @Test
    void testCreateSocial() throws UserException {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        Social social = user.getSocial();
        Assertions.assertNull(social);

        social= socialService.create(user,SocialTestCreteData.facebook,SocialTestCreteData.instrgram);

        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreteData.facebook,social.getFacebook());

    }

    @Order(4)
    @Test
    void testCreateAddress() throws UserException {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());

       createAddress(user,AddressTestCreateData.zipcode);
       createAddress(user,AddressTestCreateData2.zipcode);

    }


    void createAddress(User user,String zipcode) throws UserException {
        Address address = addressService.create(user,zipcode);

        Assertions.assertNotNull(address);
        Assertions.assertEquals(zipcode,address.getZipcode());

    }

    @Order(9)
    @Test
    void testDelete(){
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        userService.deleteById(user.getId());
        Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(optDelete.isEmpty());

    }

    interface TestCreateData {
        String email = "test222@gmail.com";
        String password = "44455777";
        String name= "For test only";

    }

    interface  SocialTestCreteData {
        String facebook = "facebook.com";
        String instrgram = "instragram";
     }

     interface  AddressTestCreateData{
        String zipcode = "83210";
     }


    interface  AddressTestCreateData2{
        String zipcode = "121111";
    }

    interface TestUpdateData {
        String name= "Update man";

    }

}
