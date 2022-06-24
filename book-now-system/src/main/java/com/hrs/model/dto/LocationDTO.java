package com.hrs.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LocationDTO {
    private long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private boolean status;
}
