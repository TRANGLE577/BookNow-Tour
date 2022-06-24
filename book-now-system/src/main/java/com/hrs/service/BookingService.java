package com.hrs.service;

import com.hrs.model.dto.BookingDTO;
import com.hrs.model.dto.BookingReportDTO;
import com.hrs.model.dto.TourDTO;
import com.hrs.model.reponse.BookingResponse;
import com.hrs.model.reponse.TourResponse;

import java.util.List;

public interface BookingService {

    List<BookingResponse> getList(String token);

    BookingResponse getById(String token, long id);

    String create(String token, BookingDTO tourDTO);

    List<BookingResponse> getReportBooking(String token, BookingReportDTO bookingReportDTO);

    List<BookingResponse> getListInProgress(String token);

    List<BookingResponse> getAllBookingByTour(String token, BookingReportDTO bookingReportDTO);


}
