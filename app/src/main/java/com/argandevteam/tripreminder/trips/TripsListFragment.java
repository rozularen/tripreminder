package com.argandevteam.tripreminder.trips;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.tripsdetail.TripDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class TripsListFragment extends Fragment implements TripsListContract.View {

    @BindView(R.id.trip_recycler_view)
    RecyclerView tripsRecyclerView;

    private static final String TAG = "TripListFragment";

    private RecyclerView.LayoutManager tripsLayoutManager;
    private TripsListContract.Presenter presenter;
    private TripsListAdapter tripsListAdapter;


    public TripsListFragment() {
        // Required empty public constructor
    }

    public static TripsListFragment newInstance() {
        return new TripsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tripsListAdapter = new TripsListAdapter(new ArrayList<Trip>(0));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        android.view.View view = inflater.inflate(R.layout.fragment_trips, container, false);

        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.result(requestCode, resultCode);
    }

    @Override
    public void setPresenter(TripsListContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void setLoadingView(boolean showLoadingView) {

    }

    @Override
    public void showTrips(List<Trip> tripsToShow) {
        tripsRecyclerView.setHasFixedSize(true);

        tripsLayoutManager = new LinearLayoutManager(getActivity());
        tripsRecyclerView.setLayoutManager(tripsLayoutManager);

        tripsListAdapter = new TripsListAdapter(tripsToShow);
        tripsRecyclerView.setAdapter(tripsListAdapter);
    }

    @Override
    public void showTripDetailsView(String tripId) {
      Intent intent = new Intent(getContext(), TripDetailsActivity.class);
        intent.putExtra(TripDetailsActivity.EXTRA_TRIP_ID, tripId);
        startActivity(intent);
    }

    @Override
    public void showCreateTrip() {

    }

    @Override
    public void showNoTrips() {

    }

    @Override
    public void showLoadingTripsError() {

    }

    @Override
    public void showSuccessfullySavedTrip() {
        Toast.makeText(getContext(), "Trip created successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
