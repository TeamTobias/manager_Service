package com.tobias.managerservice.inner.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class SaleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int salerId;
    private String name;
    private String nickname;
    private String email;
    private String gender;
    private String birth;
    private String phone;
    private String website;

}