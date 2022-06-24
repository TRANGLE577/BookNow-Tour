package com.hrs.service;

import com.hrs.model.reponse.LocationResponse;
import java.util.List;

public interface LocationService {

    List<LocationResponse> getList(String token);

    LocationResponse getById(String token, long id);

}
