package com.hrs.model.reponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingResponse {
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
    private TourResponse tour;
    private AccountResponse account;
    private PaymentResponse payment;

}
