package com.example.mobileapp.itf;

import com.example.mobileapp.model.Tour;

import java.util.List;

public interface SearchInterface {

    void onSearchSuccess(List<Tour> tours);

    void onSearchError(String error);

}
