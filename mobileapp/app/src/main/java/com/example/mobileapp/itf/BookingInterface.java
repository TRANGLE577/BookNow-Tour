package com.example.mobileapp.itf;

import com.example.mobileapp.model.Booking;

import java.util.List;

public interface BookingInterface {

    void onSuccess(String message);

    void onError(String error);

    void onBooking(String type, List<Booking> bookingList);

}
