package com.example.mobileapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.model.Tour;
import com.example.mobileapp.ui.DetailActivity;
import com.example.mobileapp.util.ContantUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TourHorizontalAdapter extends RecyclerView.Adapter<TourHorizontalAdapter.TourViewHolder> {

    private Context context;
    private List<Tour> tourList;

    public TourHorizontalAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_horizontal, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.tvName.setText(tour.getName());
        Picasso.with(context).load(ContantUtil.HOST_URL + "/" +tour.getImage()).into(holder.imgImage);
        holder.tvLocation.setText("Địa điểm: " + tour.getLocation().getName());
        holder.tvPriceAdult.setText("Giá vé: " + tour.getTourAdultCost());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, DetailActivity.class);
                i.putExtra("tourId",tour.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tourList == null) {
            return 0;
        }
        return tourList.size();
    }

    public static final class TourViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgImage, imgShow;
        private TextView tvName, tvLocation, tvPriceAdult;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);

            imgImage = itemView.findViewById(R.id.imgImage);
            imgShow = itemView.findViewById(R.id.imgShow);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPriceAdult = itemView.findViewById(R.id.tvPriceAdult);
        }
    }

}
