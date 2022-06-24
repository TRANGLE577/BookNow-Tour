package com.example.mobileapp.api;

import com.example.mobileapp.itf.ForgotPasswordInterface;
import com.example.mobileapp.dto.ReponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordAPI {

    private ForgotPasswordInterface forgotPasswordInterface = null;

    private APIService apiService;

    public ForgotPasswordAPI(ForgotPasswordInterface forgotPasswordInterface) {
        this.forgotPasswordInterface = forgotPasswordInterface;
        apiService = APIClient.getAPIService();
    }

    public void forgotPassword(String username) {
        apiService.forgotPassword(username).enqueue(new Callback<ReponseDTO<String>>() {
            @Override
            public void onResponse(Call<ReponseDTO<String>> call, Response<ReponseDTO<String>> response) {
                ReponseDTO<String> reponseDTO = response.body();
                if (reponseDTO != null && reponseDTO.getCode().equalsIgnoreCase("success")) {
                    forgotPasswordInterface.onSuccess(reponseDTO.getCode());
                } else {
                    forgotPasswordInterface.onError(null);
                }
            }

            @Override
            public void onFailure(Call<ReponseDTO<String>> call, Throwable t) {
                forgotPasswordInterface.onError(t.getMessage());
            }
        });
    }

}
