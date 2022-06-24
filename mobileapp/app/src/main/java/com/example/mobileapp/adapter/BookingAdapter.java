package com.example.mobileapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.api.APIClient;
import com.example.mobileapp.api.APIService;
import com.example.mobileapp.dto.BookingDTO;
import com.example.mobileapp.itf.MainInterface;
import com.example.mobileapp.model.Booking;
import com.example.mobileapp.model.Tour;
import com.example.mobileapp.util.ContantUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.TopPlacesViewHolder> {

    private Context context = null;
    private LayoutInflater layoutInflater = null;
    private MainInterface mainInterface = null;
    private AlertDialog alertDialog = null;
    private String type;
    private List<Booking> bookingList;

    public BookingAdapter(Context context, LayoutInflater layoutInflater, MainInterface mainInterface,
                          String type, List<Booking> bookingList) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.mainInterface = mainInterface;
        this.type = type;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_booking, parent, false);
        return new TopPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlacesViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.tvName.setText(booking.getTour().getName());
        Picasso.with(context).load(ContantUtil.HOST_URL + "/" + booking.getTour().getImage()).into(holder.imgImage);
        holder.tvLocation.setText("Thanh toán: " + booking.getPayment().getName());
        holder.tvPriceAdult.setText("Vé người lớn: " + booking.getTour().getTourAdultCost());
        holder.tvPriceChildren.setText("Vé trẻ em: " + booking.getTour().getTourChildrenCost());

        switch (booking.getStatus()) {
            case 1:
                holder.imgShow.setBackgroundColor(Color.GREEN);
                break;
            case 0:
                holder.imgShow.setBackgroundColor(Color.YELLOW);
                break;
            case -1:
                holder.imgShow.setBackgroundColor(Color.RED);
                break;
            default:
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equalsIgnoreCase("BOOKING")) {
                    showAlertDialog(view, booking.getId(), booking.getTour());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bookingList == null) {
            return 0;
        }
        return bookingList.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgImage, imgShow;
        private TextView tvName, tvLocation, tvPriceAdult, tvPriceChildren;

        public TopPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgImage = itemView.findViewById(R.id.imgImage);
            imgShow = itemView.findViewById(R.id.imgShow);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPriceAdult = itemView.findViewById(R.id.tvPriceAdult);
            tvPriceChildren = itemView.findViewById(R.id.tvPriceChildren);
        }
    }

    public void showAlertDialog(View view, long bookingId, Tour tour) {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(tour.getName());

        // set the custom layout
        final View customLayout = layoutInflater.inflate(R.layout.dialog_booking_cancle, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton(
                "Huỷ Vé",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        APIService apiService = APIClient.getAPIService();
                        BookingDTO bookingDTO = new BookingDTO();
                        bookingDTO.setId(bookingId);
                        bookingDTO.setStatus(-1);
                        apiService.bookingCancle(bookingDTO, ContantUtil.getAccessToken()).enqueue(new Callback<Booking>() {
                            @Override
                            public void onResponse(Call<Booking> call, Response<Booking> response) {
                                mainInterface.reloadPage();
                            }

                            @Override
                            public void onFailure(Call<Booking> call, Throwable t) {
                                mainInterface.reloadPage();
                            }
                        });
                    }
                });
        builder.setNegativeButton("Giữ Vé", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        // create and show the alert dialog
        alertDialog = builder.create();
        alertDialog.show();
    }

}