package com.example.mobileapp.api;

import com.example.mobileapp.itf.LoginInterface;
import com.example.mobileapp.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAPI {

    private LoginInterface loginInterface = null;

    private APIService apiService;

    public LoginAPI(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        apiService = APIClient.getAPIService();
    }

    public void login(String username, String password) {
        apiService.loginAccount(username, password).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Account account = response.body();
                if (account == null) {
                    loginInterface.loginError(null);
                } else {
                    loginInterface.loginSuccess(account);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                loginInterface.loginError(t.getMessage());
            }
        });
    }

}
