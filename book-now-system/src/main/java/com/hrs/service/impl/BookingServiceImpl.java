package com.hrs.service.impl;

import com.hrs.model.dto.BookingDTO;
import com.hrs.model.dto.BookingReportDTO;
import com.hrs.model.reponse.BookingResponse;
import com.hrs.service.BookingService;
import com.hrs.utils.ConstantUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<BookingResponse> getList(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/bookings";
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            List<BookingResponse> resultList = (List<BookingResponse>) response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public BookingResponse getById(String token, long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/bookings/id/" + id;
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<BookingResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, BookingResponse.class);
            BookingResponse resultList = response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String create(String token, BookingDTO bookingDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/bookings/save";
            HttpEntity<BookingDTO> entity = new HttpEntity<>(bookingDTO, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            String result = response.getBody();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<BookingResponse> getReportBooking(String token, BookingReportDTO bookingReportDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/bookings/getReportBooking";
            HttpEntity<BookingReportDTO> entity = new HttpEntity<>(bookingReportDTO, headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
            List<BookingResponse> resultList = (List<BookingResponse>) response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<BookingResponse> getListInProgress(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/bookings/inprogress";
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            List<BookingResponse> resultList = (List<BookingResponse>) response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<BookingResponse> getAllBookingByTour(String token, BookingReportDTO bookingReportDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/bookings/getAllBookingByTour";
            HttpEntity<BookingReportDTO> entity = new HttpEntity<>(bookingReportDTO, headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
            List<BookingResponse> resultList = (List<BookingResponse>) response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

}
