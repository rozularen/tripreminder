package com.argandevteam.tripreminder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TripsFragment extends Fragment {

    @BindView(R.id.trip_recycler_view)
    RecyclerView tripsRecyclerView;

    private RecyclerView.LayoutManager tripsLayoutManager;
    private TripsAdapter tripsAdapter;

    public TripsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trips, container, false);

        ButterKnife.bind(this, view);
        List<Trip> tripList = new ArrayList<>();

        tripList.add(new Trip("Holddd"));

        tripsRecyclerView.setHasFixedSize(true);

        tripsLayoutManager = new LinearLayoutManager(getActivity());
        tripsRecyclerView.setLayoutManager(tripsLayoutManager);

        tripsAdapter = new TripsAdapter(tripList);
        tripsRecyclerView.setAdapter(tripsAdapter);

        return view;
    }

}
