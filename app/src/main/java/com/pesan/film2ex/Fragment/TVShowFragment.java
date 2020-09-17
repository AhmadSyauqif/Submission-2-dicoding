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
import com.pesan.film2ex.Adapter.TvAdapter;
import com.pesan.film2ex.Model.TvShow;
import com.pesan.film2ex.R;

import java.util.ArrayList;

public class TVShowFragment extends Fragment {

    private String[] titleTv;
    private String[] yearTv;
    private String[] descriptionTv;
    private TypedArray posterTv;
    private TvAdapter tvAdapter;
    private RecyclerView rvTv;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void addItem() {
        ArrayList<TvShow> tvArrayList = new ArrayList<> ();
        for (int a = 0; a < titleTv.length; a++) {
            TvShow tvShow = new TvShow();
            tvShow.setTitle(titleTv[a]);
            tvShow.setYear(yearTv[a]);
            tvShow.setDescription(descriptionTv[a]);
            tvShow.setPoster(posterTv.getResourceId(a, -1));
            tvArrayList.add(tvShow);
        }

        tvAdapter.setListData(tvArrayList);
    }

    private void prepareData() {
        titleTv = getActivity().getResources().getStringArray(R.array.title_tv);
        descriptionTv = getActivity().getResources().getStringArray(R.array.description_tv);
        yearTv = getActivity().getResources().getStringArray(R.array.year_tv);
        posterTv = getActivity().getResources().obtainTypedArray(R.array.poster_tv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTv = view.findViewById(R.id.rv_tv);
        tvAdapter = new TvAdapter(getActivity());

        prepareData();
        addItem();
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvTv.setLayoutManager(new LinearLayoutManager (getActivity()));
        rvTv.setAdapter(tvAdapter);

        tvAdapter.setOnItemClickCallback(new TvAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow data) {
                showSelectedTv(data);
            }
        });
    }

    private void showSelectedTv(TvShow data) {
        Intent intent = new Intent (getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_DETAIL_DATA, data);
        intent.putExtra(DetailActivity.KEY_JENIS_DATA, "tv");
        startActivity(intent);
    }
}
