package com.argandevteam.tripreminder.tripsdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.createedittrip.CreateEditTripFragment;
import com.argandevteam.tripreminder.trips.ActivityContract;
import com.argandevteam.tripreminder.trips.TripsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailsFragment extends Fragment implements TripDetailsContract.View {

    @NonNull
    private static final String ARG_TRIP_ID = "TRIP_ID";
    @NonNull
    private static final int REQUEST_EDIT_TRIP = 1;

    @BindView(R.id.trip_title)
    TextView mTripTitle;

    private TripDetailsContract.Presenter mPresenter;
    private ActivityContract.Presenter mActivityPresenter;

    public TripDetailsFragment() {
        // Required empty public constructor
    }

    public static TripDetailsFragment newInstance(@NonNull String tripId) {
        Bundle args = new Bundle();
        args.putString(ARG_TRIP_ID, tripId);
        TripDetailsFragment tripDetailsFragment = new TripDetailsFragment();
        tripDetailsFragment.setArguments(args);
        return tripDetailsFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_details, container, false);

        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        //Set up Floating Action Button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_create_trip);

        fab.setImageResource(R.drawable.ic_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.editTrip();
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_trip:
                mPresenter.deleteTrip();
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.details_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setPresenter(TripDetailsContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void setActivityPresenter(ActivityContract.Presenter mActivityPresenter) {
        this.mActivityPresenter = mActivityPresenter;
    }

    @Override
    public void showEditTrip(String tripId) {
        mActivityPresenter.showEditTrip(tripId);
    }

    @Override
    public void showMissingTrip() {
        mTripTitle.setText("MISSING DATA");

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showTitle(String title) {
        mTripTitle.setVisibility(View.VISIBLE);
        mTripTitle.setText(title);
    }

    @Override
    public void showTripDeleted() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new TripsFragment())
                .commit();
    }


}
