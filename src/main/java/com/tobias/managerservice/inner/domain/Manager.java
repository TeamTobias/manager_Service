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
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nickname;
    private String email;
    private String gender;
    private String birth;
    private String phone;

    public static Manager createManager(RequestManager requestManager){
        Manager manager = new Manager();
        manager.setName(requestManager.getName());
        manager.setNickname(requestManager.getNickname());
        manager.setEmail(requestManager.getEmail());
        manager.setGender(requestManager.getGender());
        manager.setBirth(requestManager.getBirth());
        manager.setPhone(requestManager.getPhone());
        return manager;
    }
}
