package com.hrs.service;

import com.hrs.model.dto.AccountDTO;
import com.hrs.model.dto.LoginDTO;
import com.hrs.model.dto.TourDTO;
import com.hrs.model.reponse.AccountResponse;
import com.hrs.model.reponse.UserResponse;

import java.util.List;

public interface AccountService {

    String getHome();

    UserResponse login(LoginDTO loginDTO);

    List<AccountResponse> getList(String token);

    AccountResponse getById(String token, long id);

    String register(AccountDTO accountDTO);

    String save(AccountDTO accountDTO, String token);

    String deleteAccount(String token, AccountDTO accountDTO);

}
