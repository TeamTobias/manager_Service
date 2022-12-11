package com.tobias.managerservice.inner.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseManager {
    private int id;
    private String name;
    private String nickname;
    private String email;
    private Manager.Gender gender;
    private Date birth;
    private String phone;
}
