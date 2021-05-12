package com.example.simplespringboot.api;

import com.example.simplespringboot.business.ChatBusiness;
import com.example.simplespringboot.exception.ChatException;
import com.example.simplespringboot.model.ChatMessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")

public class ChatApi {

    private final ChatBusiness business;

    public ChatApi(ChatBusiness business) {
        this.business = business;
    }

    @PostMapping("/message")
    public ResponseEntity<Void> post (@RequestBody ChatMessageRequest request) throws ChatException {
        System.out.println(request);
        business.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
