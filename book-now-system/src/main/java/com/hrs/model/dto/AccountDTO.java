package com.hrs.model.dto;

import com.hrs.model.reponse.RoleResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountDTO {
    private long id;
    private long roleId;
    private String fullname;
    private String image;
    private String email;
    private String username;
    private String password;
    private String phone;
    private boolean status;
}
