package com.hrs.service;

import com.hrs.model.dto.AccountDTO;
import com.hrs.model.dto.TourDTO;
import com.hrs.model.dto.TourReportDTO;
import com.hrs.model.reponse.AccountResponse;
import com.hrs.model.reponse.TourResponse;

import java.util.List;

public interface TourService {

    List<TourResponse> getAll();
    TourResponse getById(long id);

    List<TourResponse> getList(String token);

    TourResponse getById(String token, long id);

    TourResponse save(String token, TourDTO tourDTO);

    List<TourResponse> getReportTour(String token, TourReportDTO tourReportDTO);

    String deleteTour(String token, TourDTO tourDTO);
}
