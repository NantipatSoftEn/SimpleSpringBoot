package com.example.simplespringboot.api;

import com.example.simplespringboot.business.TestBusiness;
import com.example.simplespringboot.model.MRegisterRequest;
import com.example.simplespringboot.model.TestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestApi {

    // method 1 Field Injection
//    @Autowired
//    private TestBusiness business;
    // method 2 contructer Injection alt+enter
    private  final TestBusiness business;

    public TestApi(TestBusiness business) {
        this.business = business;
    }


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
    public ResponseEntity<String> register(@RequestBody MRegisterRequest request){
        String res = null;
        try{
            res = business.register(request);
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

         return ResponseEntity.ok(res);
    }
}
