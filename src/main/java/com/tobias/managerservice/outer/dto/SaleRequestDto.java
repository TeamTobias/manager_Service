package com.tobias.managerservice.outer.dto;

import com.tobias.managerservice.inner.domain.Manager;
import com.tobias.managerservice.inner.domain.SaleRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SaleRequestDto implements Serializable {
    private int id;
    private String name;
    private String nickname;
    private String email;
    private SaleRequest.Gender gender;
    private Date birth;
    private String phone;
    private String website;
}


