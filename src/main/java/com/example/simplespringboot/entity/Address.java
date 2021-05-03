package com.example.simplespringboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m_address")
public class Address extends  BaseEntity {

    @Column(length = 60)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name="m_user_id",nullable = false)
    private  User user;
}

