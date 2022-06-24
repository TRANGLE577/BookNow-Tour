package com.hrs.service.impl;

import com.hrs.model.reponse.LocationResponse;
import com.hrs.service.LocationService;
import com.hrs.utils.ConstantUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<LocationResponse> getList(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/locations";
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            List<LocationResponse> resultList = (List<LocationResponse>)  response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public LocationResponse getById(String token, long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/locations/id/"+id;
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<LocationResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, LocationResponse.class);
            LocationResponse resultList = response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

}
