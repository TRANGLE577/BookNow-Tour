package com.example.mobileapp.api;

import com.example.mobileapp.dto.BookingDTO;
import com.example.mobileapp.model.Account;
import com.example.mobileapp.dto.ReponseDTO;
import com.example.mobileapp.model.Booking;
import com.example.mobileapp.model.Tour;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("/api/tours")
    Call<List<Tour>> getTours();

    @GET("/api/tours/random")
    Call<List<Tour>> getToursByRandom();

    @GET("/api/tours/id/{id}")
    Call<Tour> getTourById(@Path("id") long id);

    @GET("/api/tours/search")
    Call<List<Tour>> getTourBySearch(@Query("keyword") String keyword);

    @FormUrlEncoded
    @POST("/api/auth/login")
    Call<Account> loginAccount(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/auth/register/v2")
    Call<ReponseDTO<Account>> registerAccount(@Field("fullname") String fullname, @Field("username") String username,
         @Field("password") String password, @Field("email") String email, @Field("roleId") long roleId);

    @FormUrlEncoded
    @POST("/api/auth/forgotpassword")
    Call<ReponseDTO<String>> forgotPassword(@Field("username") String username);

    @POST("/api/bookings/save")
    Call<Booking> booking(@Body BookingDTO bookingDTO, @Header("x-access-token") String accessToken);

    @POST("/api/bookings/cancle")
    Call<Booking> bookingCancle(@Body BookingDTO bookingDTO, @Header("x-access-token") String accessToken);

    @GET("/api/bookings/booking/user/{id}")
    Call<List<Booking>> getBookings(@Path("id") long id, @Header("x-access-token") String accessToken);

    @GET("/api/bookings/history/user/{id}")
    Call<List<Booking>> getHistorys(@Path("id") long id, @Header("x-access-token") String accessToken);

}
