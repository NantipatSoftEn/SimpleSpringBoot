package com.example.simplespringboot.model;
import lombok.Data;

@Data
public class EmailRequest {

    private String to;

    private String subject;

    private String content;

}
