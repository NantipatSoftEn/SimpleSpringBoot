package com.example.simplespringboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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
    @OneToOne(mappedBy = "user",orphanRemoval = true) //  ถ้า user โดนลบ social โดนลบด้วย
    private Social social;
    // FetchType.LAZY จะไม่ดึง relation ติดมาด้วย
    // FetchType.EAGER address จะถูกเก็บใน memory
    @OneToMany(mappedBy = "user",orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Address> addresses;
    private boolean activated;
}

