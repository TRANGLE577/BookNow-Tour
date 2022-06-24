package com.example.mobileapp.api;

import com.example.mobileapp.api.APIClient;
import com.example.mobileapp.api.APIService;
import com.example.mobileapp.dto.BookingDTO;
import com.example.mobileapp.itf.BookingInterface;
import com.example.mobileapp.model.Booking;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAPI {

    private BookingInterface bookingInterface = null;
    private APIService apiService;

    public BookingAPI(BookingInterface bookingInterface) {
        this.bookingInterface = bookingInterface;
        apiService = APIClient.getAPIService();
    }

    public void booking(BookingDTO bookingDTO, String accessToken) {
        apiService.booking(bookingDTO, accessToken).enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                Booking booking = response.body();
                if (booking != null) {
                    bookingInterface.onSuccess("");
                } else {
                    bookingInterface.onError(null);
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                bookingInterface.onError(t.getMessage());
            }
        });
    }

    public void getBookingsByAccount(long accountId, String accessToken) {
        apiService.getBookings(accountId, accessToken).enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                List<Booking> bookings = response.body();
                if (bookings != null) {
                    bookingInterface.onBooking("booking", bookings);
                } else {
                    bookingInterface.onError(null);
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                bookingInterface.onError(t.getMessage());
            }
        });
    }

    public void getHistoryByAccount(long accountId, String accessToken) {
        apiService.getHistorys(accountId, accessToken).enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                List<Booking> bookings = response.body();
                if (bookings != null) {
                    bookingInterface.onBooking("booking", bookings);
                } else {
                    bookingInterface.onError(null);
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                bookingInterface.onError(t.getMessage());
            }
        });
    }

}
