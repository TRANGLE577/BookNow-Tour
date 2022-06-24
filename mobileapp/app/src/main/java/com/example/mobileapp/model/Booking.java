package com.example.mobileapp.model;

import com.google.gson.annotations.SerializedName;

public class Booking {

    @SerializedName("id")
    private long id;

    @SerializedName("tourId")
    private long tourId;

    @SerializedName("accountId")
    private long accountId;

    @SerializedName("paymentId")
    private long paymentId;

    @SerializedName("tour")
    private Tour tour;

    @SerializedName("payment")
    private Payment payment;

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

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
