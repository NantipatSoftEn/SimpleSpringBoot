package com.example.simplespringboot.api;

import com.example.simplespringboot.business.UserBusiness;
import com.example.simplespringboot.entity.User;
import com.example.simplespringboot.exception.BaseException;
import com.example.simplespringboot.exception.FileException;
import com.example.simplespringboot.exception.UserException;
import com.example.simplespringboot.model.*;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request) throws BaseException {
        String  response = business.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse res = business.register(request);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/refresh-token")
    public ResponseEntity<String>  refreshToken() throws UserException {
        String response = business.refreshToken();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RequestMapping("/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws FileException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
