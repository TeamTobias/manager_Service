package com.tobias.managerservice.inner.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseManager {
    private int id;
    private String name;
    private String nickname;
    private String email;
    private String gender;
    private String birth;
    private String phone;
}
