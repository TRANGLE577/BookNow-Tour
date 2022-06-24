package com.hrs.model.reponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoleResponse {
    private long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private boolean status;

}
