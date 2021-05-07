package com.example.simplespringboot.business;

import com.example.simplespringboot.exception.ChatException;
import com.example.simplespringboot.model.ChatMessage;
import com.example.simplespringboot.model.ChatMessageRequest;
import com.example.simplespringboot.util.SecurityUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatBusiness {
    private  final SimpMessagingTemplate template;

    public ChatBusiness(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post (ChatMessageRequest request) throws ChatException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if(opt.isEmpty()){
            throw ChatException.accessDenied();
        }
        // TODO: validate message

        final  String destination = "chat";
        ChatMessage payload = new ChatMessage();
        payload.setFrom(opt.get());
        payload.setMessage(request.getMessage());
        template.convertAndSend(destination,payload);
    }
}
