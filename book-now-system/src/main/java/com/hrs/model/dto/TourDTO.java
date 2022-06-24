package com.hrs.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class TourDTO {
    private long id;
    private long locationId;
    private String name;
    private String description;
    private MultipartFile image;
    private String tourDateDepart;
    private String tourDateReturn;
    private long tourAdultCost;
    private long tourChildrenCost;
    private boolean status;
    private boolean isFinish;

    // custom param
    private String cusTourDateDepart;
    private String cusTourDateReturn;
}
