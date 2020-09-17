package com.pesan.film2ex.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pesan.film2ex.Model.TvShow;
import com.pesan.film2ex.R;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.Holder> implements View.OnClickListener {

    private ArrayList<TvShow> listData;
    private Context context;

    private TvAdapter.OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(TvAdapter.OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setListData(ArrayList<TvShow> listData) {
        this.listData = listData;
    }

    public TvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TvAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tvshow, viewGroup, false);
        return new TvAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvAdapter.Holder holder, int i) {
        holder.titleTv.setText(listData.get(i).getTitle());
        holder.yearTv.setText(listData.get(i).getYear());
        Glide.with(context).load(listData.get(i).getPoster()).into(holder.posterTv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listData.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView titleTv, yearTv;
        ImageView posterTv;

        Holder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.title_tv);
            yearTv = itemView.findViewById(R.id.year_tv);
            posterTv = itemView.findViewById(R.id.poster_tv);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(TvShow data);
    }
}
