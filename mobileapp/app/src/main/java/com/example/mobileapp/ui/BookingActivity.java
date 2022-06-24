package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mobileapp.MainActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.adapter.BookingAdapter;
import com.example.mobileapp.itf.BookingInterface;
import com.example.mobileapp.itf.MainInterface;
import com.example.mobileapp.model.Booking;
import com.example.mobileapp.api.BookingAPI;
import com.example.mobileapp.util.ContantUtil;

import java.util.List;

public class BookingActivity extends AppCompatActivity implements BookingInterface, MainInterface {

    private BookingAdapter bookingAdapter;

    private RecyclerView rvListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mobileapp.R.layout.activity_booking);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // load data
        loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        BookingAPI bookingAPI = new BookingAPI(this);
        bookingAPI.getBookingsByAccount(ContantUtil.getAccount().getId(), ContantUtil.getAccessToken());
    }

    private void setView(List<Booking> bookingList){
        rvListItem = findViewById(R.id.rvListItem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvListItem.setLayoutManager(layoutManager);
        bookingAdapter = new BookingAdapter(this, getLayoutInflater(),
                BookingActivity.this, "BOOKING", bookingList);
        rvListItem.setAdapter(bookingAdapter);
    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onError(String error) {
        Toast.makeText(BookingActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBooking(String type, List<Booking> bookingList) {
        setView(bookingList);
    }

    @Override
    public void reloadPage() {
        // load data
        loadData();
    }

}