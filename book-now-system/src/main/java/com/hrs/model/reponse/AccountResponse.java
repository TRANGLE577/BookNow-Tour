package com.hrs.model.reponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountResponse {
    private long id;
    private long roleId;
    private RoleResponse role;
    private String fullname;
    private String image;
    private String email;
    private String username;
    private String password;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
    private boolean status;
}
