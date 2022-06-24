package com.example.mobileapp.api;

import com.example.mobileapp.itf.RegisterInterface;
import com.example.mobileapp.model.Account;
import com.example.mobileapp.dto.ReponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAPI {

    private RegisterInterface registerInterface = null;

    private APIService apiService;

    public RegisterAPI(RegisterInterface registerInterface) {
        this.registerInterface = registerInterface;
        apiService = APIClient.getAPIService();
    }

    public void register(String fullname, String username, String password, String email, long roleId) {
        apiService.registerAccount(fullname, username, password, email,roleId).enqueue(new Callback<ReponseDTO<Account>>() {
            @Override
            public void onResponse(Call<ReponseDTO<Account>> call, Response<ReponseDTO<Account>> response) {
                if (response.code() == 400) {
                    registerInterface.onError(response.message());
                } else {
                    ReponseDTO<Account> reponseDTO = response.body();
                    if (reponseDTO != null && reponseDTO.getCode().equalsIgnoreCase("success")) {
                        Account account = reponseDTO.getData();
                        registerInterface.onSuccess(account);
                    } else {
                        registerInterface.onError(reponseDTO.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ReponseDTO<Account>> call, Throwable t) {
                registerInterface.onError(t.getMessage());
            }
        });
    }

}
