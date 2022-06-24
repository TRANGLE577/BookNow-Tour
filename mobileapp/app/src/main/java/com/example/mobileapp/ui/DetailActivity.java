package com.example.mobileapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.MainActivity;
import com.example.mobileapp.R;
import com.example.mobileapp.api.APIClient;
import com.example.mobileapp.api.APIService;
import com.example.mobileapp.dto.BookingDTO;
import com.example.mobileapp.itf.BookingInterface;
import com.example.mobileapp.model.Booking;
import com.example.mobileapp.model.Tour;
import com.example.mobileapp.api.BookingAPI;
import com.example.mobileapp.util.ContantUtil;
import com.example.mobileapp.util.InputFilterMinMax;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements BookingInterface {

    private APIService apiService;

    private ImageView imgTour;
    private TextView tvName, tvPriceAdult, tvPriceChildren, tvDateTime, tvDescription;
    private Button btnBooking;

    private Tour tour = null;

    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Long tourId = getIntent().getLongExtra("tourId", 0);

        imgTour = findViewById(R.id.imgTour);
        tvName = findViewById(R.id.tvName);
        tvPriceAdult = findViewById(R.id.tvPriceAdult);
        tvPriceChildren = findViewById(R.id.tvPriceChildren);
        btnBooking = findViewById(R.id.btnBooking);
        tvDateTime = findViewById(R.id.tvDateTime);
        tvDescription = findViewById(R.id.tvDescription);

        apiService = APIClient.getAPIService();
        apiService.getTourById(tourId).enqueue(new Callback<Tour>() {
            @Override
            public void onResponse(Call<Tour> call, Response<Tour> response) {
                Tour tour = response.body();
                if (tour != null) {
                    setView(tour);
                }
            }

            @Override
            public void onFailure(Call<Tour> call, Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContantUtil.getAccount() == null) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                } else {
                    showAlertDialog(getCurrentFocus());
                }
            }
        });
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

    private void setView(Tour tour) {
        this.tour = tour;
        getSupportActionBar().setTitle(tour.getName());
        Picasso.with(getApplicationContext()).load(ContantUtil.HOST_URL + "/" + tour.getImage()).into(imgTour);
        tvName.setText(tour.getName());
        tvPriceAdult.setText("Vé người lớn: " + String.valueOf(tour.getTourAdultCost()));
        tvPriceChildren.setText("Vé trẻ em: " + String.valueOf(tour.getTourChildrenCost()));
        tvDateTime.setText("Từ ngày: " + tour.getTourDateDepart() + " - Đến ngày: " +tour.getTourDateReturn());
        tvDescription.setText(tour.getDescription());
    }

    public void showAlertDialog(View view) {
        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);
        builder.setTitle(tour.getName());

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_booking, null);
        builder.setView(customLayout);

        // send data
        EditText edtQtyAdult = customLayout.findViewById(R.id.edtQtyAdult);
        EditText edtQtyChildren = customLayout.findViewById(R.id.edtQtyChildren);
        EditText edtTotal = customLayout.findViewById(R.id.edtTotal);

        TextView tvTotalAdult = customLayout.findViewById(R.id.tvTotalAdult);
        TextView tvTotalChildren = customLayout.findViewById(R.id.tvTotalChildren);

        tvTotalAdult.setText("x " + tour.getTourAdultCost());
        tvTotalChildren.setText("x " + tour.getTourChildrenCost());

        // set min and max
        edtQtyAdult.setFilters(new InputFilter[]{new InputFilterMinMax("0", "5")});
        edtQtyChildren.setFilters(new InputFilter[]{new InputFilterMinMax("0", "5")});

        // set total price
        edtQtyAdult.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // if the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    int qtyAdult = parseValue(edtQtyAdult.getText().toString());
                    int qtyChildren = parseValue(edtQtyChildren.getText().toString());
                    double total = qtyAdult * tour.getTourAdultCost() + qtyChildren * tour.getTourChildrenCost();
                    edtTotal.setText(String.valueOf(total));
                    return true;
                }
                return false;
            }
        });

        // set total price
        edtQtyChildren.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // if the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    int qtyAdult = parseValue(edtQtyAdult.getText().toString());
                    int qtyChildren = parseValue(edtQtyChildren.getText().toString());
                    double total = qtyAdult * tour.getTourAdultCost() + qtyChildren * tour.getTourChildrenCost();
                    edtTotal.setText(String.valueOf(total));
                    return true;
                }
                return false;
            }
        });

        // add a button
        builder.setPositiveButton(
                "Đặt Vé",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (edtQtyAdult.getText().toString().equalsIgnoreCase("0")
                                && edtQtyChildren.getText().toString().equalsIgnoreCase("0")) {
                            Toast.makeText(DetailActivity.this, "Vui lòng nhập số lượng vé!", Toast.LENGTH_LONG).show();
                            alertDialog.show();
                        } else {
                            BookingAPI bookingAPI = new BookingAPI(DetailActivity.this);
                            BookingDTO bookingDTO = new BookingDTO();
                            bookingDTO.setAccountId(ContantUtil.getAccount().getId());
                            bookingDTO.setTourId(tour.getId());
                            bookingDTO.setPaymentId(1);
                            int qtyAdult = parseValue(edtQtyAdult.getText().toString());
                            int qtyChildren = parseValue(edtQtyChildren.getText().toString());
                            double total = qtyAdult * tour.getTourAdultCost() + qtyChildren * tour.getTourChildrenCost();
                            bookingDTO.setQuantityAdult(qtyAdult);
                            bookingDTO.setQuantityChildren(qtyChildren);
                            bookingDTO.setTourAdultCost(tour.getTourAdultCost());
                            bookingDTO.setTourChildrenCost(tour.getTourChildrenCost());
                            bookingDTO.setTotalCost(total);
                            bookingDTO.setStatus(0);
                            bookingAPI.booking(bookingDTO, ContantUtil.getAccessToken());
                            dialog.cancel();
                        }
                    }
                });
        builder.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        // create and show the alert dialog
        alertDialog = builder.create();
        alertDialog.show();
    }

    private int parseValue(String text) {
        try {
            return Integer.parseInt(text.toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public void onSuccess(String message) {
        Intent i = new Intent(getApplicationContext(), BookingActivity.class);
        startActivity(i);
        Toast.makeText(DetailActivity.this, "Đăng ký thành công, trạng thái đang chờ duyệt!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(DetailActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBooking(String type, List<Booking> bookingList) {

    }

}