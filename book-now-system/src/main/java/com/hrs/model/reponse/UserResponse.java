package com.hrs.model.reponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse {
    private long id;
    private String username;
    private String role;
    private String accessToken;
}
