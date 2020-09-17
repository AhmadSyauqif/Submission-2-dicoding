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
import com.pesan.film2ex.Model.Movie;
import com.pesan.film2ex.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> implements View.OnClickListener {

    private ArrayList<Movie> listData;
    private Context context;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setListData(ArrayList<Movie> listData) {
        this.listData = listData;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int i) {
        holder.titleMovie.setText(listData.get(i).getTitle());
        holder.yearMovie.setText(listData.get(i).getYear());
        holder.posterMovie.setImageResource(listData.get(i).getPoster());
        Glide.with(context).load(listData.get(i).getPoster()).into(holder.posterMovie);

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
    public int getItemCount()  {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView titleMovie, yearMovie;
        ImageView posterMovie;

        Holder(@NonNull View itemView) {
            super(itemView);

            titleMovie = itemView.findViewById(R.id.title_movie);
            yearMovie = itemView.findViewById(R.id.year_movie);
            posterMovie = itemView.findViewById(R.id.poster_movie);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }
}
