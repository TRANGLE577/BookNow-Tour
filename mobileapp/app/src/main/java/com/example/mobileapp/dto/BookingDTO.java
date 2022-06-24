package com.example.mobileapp.dto;

import com.google.gson.annotations.SerializedName;

public class BookingDTO {

    @SerializedName("id")
    private long id;

    @SerializedName("tourId")
    private long tourId;

    @SerializedName("accountId")
    private long accountId;

    @SerializedName("paymentId")
    private long paymentId;

    @SerializedName("tourAdultCost")
    private double tourAdultCost;

    @SerializedName("tourChildrenCost")
    private double tourChildrenCost;

    @SerializedName("quantityAdult")
    private int quantityAdult;

    @SerializedName("quantityChildren")
    private int quantityChildren;

    @SerializedName("totalCost")
    private double totalCost;

    @SerializedName("status")
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTourId() {
        return tourId;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public double getTourAdultCost() {
        return tourAdultCost;
    }

    public void setTourAdultCost(double tourAdultCost) {
        this.tourAdultCost = tourAdultCost;
    }

    public double getTourChildrenCost() {
        return tourChildrenCost;
    }

    public void setTourChildrenCost(double tourChildrenCost) {
        this.tourChildrenCost = tourChildrenCost;
    }

    public int getQuantityAdult() {
        return quantityAdult;
    }

    public void setQuantityAdult(int quantityAdult) {
        this.quantityAdult = quantityAdult;
    }

    public int getQuantityChildren() {
        return quantityChildren;
    }

    public void setQuantityChildren(int quantityChildren) {
        this.quantityChildren = quantityChildren;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
