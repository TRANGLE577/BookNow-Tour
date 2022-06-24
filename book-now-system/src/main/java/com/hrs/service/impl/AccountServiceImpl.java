package com.hrs.service.impl;

import com.hrs.model.dto.AccountDTO;
import com.hrs.model.dto.LoginDTO;
import com.hrs.model.dto.TourDTO;
import com.hrs.model.reponse.AccountResponse;
import com.hrs.model.reponse.UserResponse;
import com.hrs.service.AccountService;
import com.hrs.utils.ConstantUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getHome() {
        String url = ConstantUtils.HOST_URL;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    @Override
    public UserResponse login(LoginDTO loginDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<LoginDTO> entity = new HttpEntity<>(loginDTO, headers);
            String url = ConstantUtils.HOST_URL + "api/auth/login";
            ResponseEntity<UserResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, UserResponse.class);
            UserResponse result = (UserResponse) response.getBody();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<AccountResponse> getList(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/accounts";
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            List<AccountResponse> resultList = (List<AccountResponse>)  response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public AccountResponse getById(String token, long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            String url = ConstantUtils.HOST_URL + "api/accounts/id/"+id;
            HttpEntity<String> entity = new HttpEntity<>(token, headers);
            ResponseEntity<AccountResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, AccountResponse.class);
            AccountResponse resultList = response.getBody();
            return resultList;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String register(AccountDTO accountDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<AccountDTO> entity = new HttpEntity<>(accountDTO, headers);
            String url = ConstantUtils.HOST_URL + "api/auth/register";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String save(AccountDTO accountDTO, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-access-token", token);
            HttpEntity<AccountDTO> entity = new HttpEntity<>(accountDTO, headers);
            String url = ConstantUtils.HOST_URL + "api/auth/save";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public String deleteAccount(String token, AccountDTO accountDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("x-access-token", token);
        String url = ConstantUtils.HOST_URL + "api/accounts/deleteAccount";
        HttpEntity<AccountDTO> entity = new HttpEntity<>(accountDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

}
