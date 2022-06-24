package com.example.mobileapp.itf;

import com.example.mobileapp.model.Account;

public interface RegisterInterface {

    void onSuccess(Account account);

    void onError(String error);

}
