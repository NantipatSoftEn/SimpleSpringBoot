package com.example.simplespringboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m_social")
public class Social extends  BaseEntity {

    @Column(length = 60)
    private String facebook;
    @Column(length = 120)
    private String instagram;

    @OneToOne
    @JoinColumn(name = "m_user_id")
    private User user;
}
