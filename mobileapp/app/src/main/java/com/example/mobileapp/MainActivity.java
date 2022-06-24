package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.adapter.TourVerticalAdapter;
import com.example.mobileapp.adapter.TourHorizontalAdapter;
import com.example.mobileapp.api.APIClient;
import com.example.mobileapp.api.APIService;
import com.example.mobileapp.api.SearchAPI;
import com.example.mobileapp.itf.SearchInterface;
import com.example.mobileapp.model.Account;
import com.example.mobileapp.model.Tour;
import com.example.mobileapp.ui.BookingActivity;
import com.example.mobileapp.ui.HistoryActivity;
import com.example.mobileapp.ui.LoginActivity;
import com.example.mobileapp.util.ContantUtil;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchInterface {

    private TourVerticalAdapter topPlacesAdapter;
    private TourHorizontalAdapter tourAdapter;

    private RecyclerView recentRecycler, topPlacesRecycler;

    private NavigationView navigationView;

    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        apiService = APIClient.getAPIService();

        loadData();

        EditText edtSearch = findViewById(R.id.edtSearch);
        edtSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    SearchAPI searchAPI = new SearchAPI(MainActivity.this);
                    searchAPI.search(edtSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionRefresh:
                loadData();
                Toast.makeText(MainActivity.this, "Đang tải dữ liệu...", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent i = null;
        switch (id) {
            case R.id.nav_booking:
                if (ContantUtil.getAccount() == null) {
                    i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                } else {
                    i = new Intent(getApplicationContext(), BookingActivity.class);
                    startActivity(i);
                }
                break;
            case R.id.nav_history:
                if (ContantUtil.getAccount() == null) {
                    i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                } else {
                    i = new Intent(getApplicationContext(), HistoryActivity.class);
                    startActivity(i);
                }
                break;
            case R.id.nav_logout:
                ContantUtil.setAccount(null);
                ContantUtil.setAccessToken(null);

                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        setProfile();
    }

    private void loadData() {
        apiService.getTours().enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                List<Tour> tours = response.body();
                setTopPlacesRecycler(tours);
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        apiService.getToursByRandom().enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                List<Tour> tours = response.body();
                setRecentRecycler(tours);
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTopPlacesRecycler(List<Tour> tourList){
        topPlacesRecycler = findViewById(R.id.rvListItem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TourVerticalAdapter(this, tourList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);
    }

    private void setRecentRecycler(List<Tour> tourList){
        recentRecycler = findViewById(R.id.recentRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        tourAdapter = new TourHorizontalAdapter(this, tourList);
        recentRecycler.setAdapter(tourAdapter);
    }

    private void setProfile() {
        Account account = ContantUtil.getAccount();
        if (account != null) {
            View headerView = navigationView.getHeaderView(0);
            // set email
            TextView tvEmail = (TextView) headerView.findViewById(R.id.tvEmail);
            tvEmail.setText(account.getEmail());
            // set fullname
            TextView tvFullname = (TextView) headerView.findViewById(R.id.tvFullname);
            tvFullname.setText(account.getFullname());
        }
    }

    @Override
    public void onSearchSuccess(List<Tour> tours) {
        setRecentRecycler(tours);
    }

    @Override
    public void onSearchError(String error) {
        Toast.makeText(MainActivity.this, "Không tìm thấy kết quả phù hợp!", Toast.LENGTH_LONG).show();
    }

}