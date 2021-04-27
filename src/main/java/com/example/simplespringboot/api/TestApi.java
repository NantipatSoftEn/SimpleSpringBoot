package com.example.simplespringboot.api;

import com.example.simplespringboot.model.MRegisterRequest;
import com.example.simplespringboot.model.TestResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestApi {
    @GetMapping
    public TestResponse test(){
        TestResponse response = new TestResponse();
        response.setName("Army");
        response.setFood("rice");
        return response;
    }

    @GetMapping
    @RequestMapping("/2")
    public  TestResponse test2(){
        TestResponse response = new TestResponse();
        response.setName("Nat 2");
        response.setFood("KFC 2");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public String register(@RequestBody MRegisterRequest request){

        return "post" + request;
    }
}
