package com.example.simplespringboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class ChatMessage {
    private  String from;
    private  String  message;
    private Date created;
}
