package com.example.simplespringboot.api;

import com.example.simplespringboot.business.TestBusiness;
import com.example.simplespringboot.exception.BaseException;
import com.example.simplespringboot.exception.FileException;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.model.MRegisterRequest;
import com.example.simplespringboot.model.TestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestApi {

    // method 1 Field Injection
//    @Autowired
//    private TestBusiness business;
    // method 2 contructer Injection alt+enter
    private final TestBusiness business;

    public TestApi(TestBusiness business) {
        this.business = business;
    }


    @GetMapping
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setName("Army");
        response.setFood("rice");
        return response;
    }

    @GetMapping
    @RequestMapping("/2")
    public TestResponse test2() {
        TestResponse response = new TestResponse();
        response.setName("Nat 2");
        response.setFood("KFC 2");
        return response;
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody MRegisterRequest request) throws BaseException {
        String res =  res = business.register(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @RequestMapping("/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws FileException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
