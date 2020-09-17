package com.pesan.film2ex.Fragment;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pesan.film2ex.Activity.DetailActivity;
import com.pesan.film2ex.Adapter.MovieAdapter;
import com.pesan.film2ex.Model.Movie;
import com.pesan.film2ex.R;

import java.util.ArrayList;




public class MovieShowFragment extends Fragment {

    private String[] titleMovie;
    private String[] yearMovie;
    private String[] descriptionMovie;
    private TypedArray posterMovie;
    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;


    public MovieShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_show, container, false);
    }

    private void addItem() {
        ArrayList<Movie> movieArrayList = new ArrayList<> ();
        for (int a = 0; a < titleMovie.length; a++) {
            Movie movie = new Movie();
            movie.setTitle(titleMovie[a]);
            movie.setYear(yearMovie[a]);
            movie.setDescription(descriptionMovie[a]);
            movie.setPoster(posterMovie.getResourceId(a, -1));
            movieArrayList.add(movie);
        }

        movieAdapter.setListData(movieArrayList);
    }

    private void prepareData() {
        titleMovie = getActivity().getResources().getStringArray(R.array.title_movie);
        descriptionMovie = getActivity().getResources().getStringArray(R.array.description_movie);
        yearMovie = getActivity().getResources().getStringArray(R.array.year_movie);
        posterMovie = getActivity().getResources().obtainTypedArray(R.array.poster_movie);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovie = view.findViewById(R.id.rv_movie);
        movieAdapter = new MovieAdapter(getActivity());

        prepareData();
        addItem();
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvMovie.setLayoutManager(new LinearLayoutManager (getActivity()));
        rvMovie.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });
    }

    private void showSelectedMovie(Movie movie) {
        Intent intent = new Intent (getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_DETAIL_DATA, movie);
        intent.putExtra(DetailActivity.KEY_JENIS_DATA, "movie");
        startActivity(intent);
    }
}
