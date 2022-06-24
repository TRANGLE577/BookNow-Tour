package com.hrs.service.impl;

import com.hrs.model.dto.TourDTO;
import com.hrs.model.dto.TourReportDTO;
import com.hrs.model.reponse.TourResponse;
import com.hrs.service.TourService;
import com.hrs.utils.ConstantUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<TourResponse> getAll() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", "aaaa");
            String url = ConstantUtils.HOST_URL + "api/tours";
            HttpEntity<String> entity = new HttpEntity<>("aaaa", headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            List<TourResponse> resultList =
                    (List<TourResponse>)  response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public TourResponse getById(long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", "aaaa");
            String url = ConstantUtils.HOST_URL + "api/tours/id/"+id;
            HttpEntity<String> entity = new HttpEntity<>("aaaa", headers);
            ResponseEntity<TourResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TourResponse.class);
            TourResponse resultList = response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<TourResponse> getList(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/tours";
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            List<TourResponse> resultList = (List<TourResponse>)  response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public TourResponse getById(String token, long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/tours/id/"+id;
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<TourResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TourResponse.class);
            TourResponse resultList = response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public TourResponse save(String token, TourDTO tourDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/tours/save";

            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            try {
                form.add("image", tourDTO.getImage().getResource());
            } catch (Exception ex) {
                System.out.println("not update img");
            }

            form.add("locationId", tourDTO.getLocationId());
            form.add("id", tourDTO.getId());
            form.add("name", tourDTO.getName());
            form.add("description", tourDTO.getDescription());
            form.add("tourDateDepart", tourDTO.getTourDateDepart());
            form.add("tourDateReturn", tourDTO.getTourDateReturn());
            form.add("tourAdultCost", tourDTO.getTourAdultCost());
            form.add("tourChildrenCost", tourDTO.getTourChildrenCost());
            form.add("status", tourDTO.isStatus());
            form.add("isFinish", tourDTO.isFinish());

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
            ResponseEntity<TourResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, TourResponse.class);
            TourResponse result = response.getBody();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<TourResponse> getReportTour(String token, TourReportDTO tourReportDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("x-access-token", token);
        String url = ConstantUtils.HOST_URL + "api/tours/getReportTour";
        HttpEntity<TourReportDTO> entity = new HttpEntity<>(tourReportDTO, headers);
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
        List<TourResponse> resultList = (List<TourResponse>)  response.getBody();
        return resultList;
    }

    @Override
    public String deleteTour(String token, TourDTO tourDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("x-access-token", token);
        String url = ConstantUtils.HOST_URL + "api/tours/deleteTour";
        HttpEntity<TourDTO> entity = new HttpEntity<>(tourDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

}
