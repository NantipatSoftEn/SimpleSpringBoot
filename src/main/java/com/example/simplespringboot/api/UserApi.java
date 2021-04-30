package com.example.simplespringboot.api;

import com.example.simplespringboot.business.UserBusiness;
import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.BaseException;
import com.example.simplespringboot.exception.FileException;
import com.example.simplespringboot.model.MRegisterRequest;
import com.example.simplespringboot.model.MRegisterResponse;
import com.example.simplespringboot.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    // method 1 Field Injection
//    @Autowired
//    private TestBusiness business;
    // method 2 contructer Injection alt+enter
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
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
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse res = business.register(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    @RequestMapping("/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws FileException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
