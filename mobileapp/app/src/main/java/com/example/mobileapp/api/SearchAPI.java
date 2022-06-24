package com.example.mobileapp.api;

import com.example.mobileapp.itf.SearchInterface;
import com.example.mobileapp.model.Tour;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAPI {

    private SearchInterface searchInterface = null;

    private APIService apiService;

    public SearchAPI(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
        apiService = APIClient.getAPIService();
    }

    public void search(String keyword) {
        apiService.getTourBySearch(keyword).enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                try {
                    List<Tour> tours = response.body();
                    if (tours != null && !tours.isEmpty()) {
                        searchInterface.onSearchSuccess(tours);
                    } else {
                        searchInterface.onSearchError(null);
                    }
                } catch (Exception ex) {
                    searchInterface.onSearchError(ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                searchInterface.onSearchError(t.getMessage());
            }
        });
    }

}
