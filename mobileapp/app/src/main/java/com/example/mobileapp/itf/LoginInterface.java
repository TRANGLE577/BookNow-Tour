package com.example.mobileapp.itf;

import com.example.mobileapp.model.Account;

public interface LoginInterface {

    void loginSuccess(Account account);

    void loginError(String error);

}
