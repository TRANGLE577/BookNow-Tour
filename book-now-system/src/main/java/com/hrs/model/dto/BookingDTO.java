package com.hrs.model.dto;

import com.hrs.model.reponse.AccountResponse;
import com.hrs.model.reponse.PaymentResponse;
import com.hrs.model.reponse.TourResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class BookingDTO {
    private long id;
    private long tourId;
    private long accountId;
    private long paymentId;
    private double tourAdultCost;
    private double tourChildrenCost;
    private int quantityAdult;
    private int quantityChildren;
    private int totalCost;
    private Date createdAt;
    private Date updatedAt;
    private int status;
}
