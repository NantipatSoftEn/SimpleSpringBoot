package com.example.simplespringboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m_user")
public class User extends  BaseEntity {

    @Column(nullable = false,unique = true,length = 60)
    private  String email;
    @Column(nullable = false,length = 120)
    private String password;
    @Column(nullable = false,length = 120)
    private String name;
    private String civilId;
    @OneToOne(mappedBy = "user")
    private Social social;
}

