package com.hrs.model.reponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class TourResponse {
    private long id;
    private long locationId;
    private String name;
    private String description;
    private String image;
    private Date tourDateDepart;
    private Date tourDateReturn;
    private long tourAdultCost;
    private long tourChildrenCost;
    private Date createdAt;
    private Date updatedAt;
    private boolean status;
    private boolean isFinish;

    // custom param
    private String cusTourDateDepart;
    private String cusTourDateReturn;
}
