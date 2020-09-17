package com.pesan.film2ex.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.pesan.film2ex.Model.Movie;
import com.pesan.film2ex.Model.TvShow;
import com.pesan.film2ex.R;

public class DetailActivity extends AppCompatActivity {
    public static String KEY_DETAIL_DATA = "detail_data";
    public static String KEY_JENIS_DATA = "jenis_data";
    public String JenisData;
    private String title, year, description;
    private int poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView txtDetailTitle = findViewById(R.id.txt_detail_title);
        TextView txtDetailYear = findViewById(R.id.txt_detail_year);
        TextView txtDetailDescription = findViewById(R.id.txt_detail_description);
        ImageView imgDetailPoster = findViewById(R.id.img_detail_poster);

        JenisData = getIntent().getStringExtra(KEY_JENIS_DATA);

        if (JenisData.equals("movie")) {
            Movie movie = getIntent().getParcelableExtra(KEY_DETAIL_DATA);
            title = movie.getTitle();
            year = movie.getYear();
            description = movie.getDescription();
            poster = movie.getPoster();
            setTitle(title);
        } else if (JenisData.equals("tv")) {
            TvShow tvShow = getIntent().getParcelableExtra(KEY_DETAIL_DATA);
            title = tvShow.getTitle();
            year = tvShow.getYear();
            description = tvShow.getDescription();
            poster = tvShow.getPoster();
            setTitle(title);
        }

        txtDetailTitle.setText(title);
        txtDetailYear.setText(year);
        txtDetailDescription.setText(description);
        imgDetailPoster.setImageResource(poster);
        Glide.with(this).load(poster).into(imgDetailPoster);
    }
}
