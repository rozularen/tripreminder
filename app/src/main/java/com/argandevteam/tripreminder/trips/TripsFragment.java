package com.argandevteam.tripreminder.trips;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.createedittrip.CreateEditTripFragment;
import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.tripsdetail.TripDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class TripsFragment extends Fragment implements TripsContract.View {

    @BindView(R.id.trip_recycler_view)
    RecyclerView mTripsRecyclerView;

    @BindView(R.id.trips_view)
    LinearLayout mTripsView;

    @BindView(R.id.no_trips_view)
    LinearLayout mNoTripsView;

    @BindView(R.id.no_trips_main_text_view)
    TextView mNoTripsMainTextView;

    @BindView(R.id.no_trips_image_view)
    ImageView mNoTripsIcon;

    @BindView(R.id.no_trips_create_text_view)
    TextView mNoTripsCreateView;

    @BindView(R.id.trips_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String TAG = "TripListFragment";

    private RecyclerView.LayoutManager mLayoutManager;
    private TripsContract.Presenter mPresenter;
    private TripsAdapter mAdapter;
    private ActivityContract.Presenter mActivityPresenter;

    // Required empty public constructor
    public TripsFragment() {
    }

    public static TripsFragment newInstance() {
        return new TripsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Trip> tripsList = new ArrayList<>(0);

        mAdapter = new TripsAdapter(tripsList, mItemListener);
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
        View view = inflater.inflate(R.layout.fragment_trips, container, false);

        ButterKnife.bind(this, view);

        //Set up RecyclerView
        mLayoutManager = new LinearLayoutManager(getActivity());
        mTripsRecyclerView.setHasFixedSize(true);
        mTripsRecyclerView.setLayoutManager(mLayoutManager);
        mTripsRecyclerView.setAdapter(mAdapter);

        //Set up Floating Action Button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_create_trip);

        fab.setImageResource(R.drawable.ic_create);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.createTrip();
            }
        });

        //Set up SwipeRefreshLayout
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTrips(false);
            }
        });

        return view;
    }

    /**
     * Listener for clicks on Trips in the RecyclerView
     */
    TripsAdapter.TripItemListener mItemListener = new TripsAdapter.TripItemListener() {
        @Override
        public void onTripClick(Trip clickedTrip) {
            mPresenter.openTripDetails(clickedTrip);
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void setPresenter(TripsContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        }
    }

    @Override
    public void setActivityPresenter(ActivityContract.Presenter presenter) {
        if (presenter != null) {
            this.mActivityPresenter = presenter;
        }
    }

    @Override
    public void setLoadingView(final boolean showLoadingView) {
        if (getView() == null) {
            return;
        }
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(showLoadingView);
            }
        });
    }

    @Override
    public void showTrips(List<Trip> tripsToShow) {
        mAdapter.replaceData(tripsToShow);

        mTripsView.setVisibility(View.VISIBLE);
        mNoTripsView.setVisibility(View.GONE);
    }

    @Override
    public void showTripDetailsView(String tripId) {

        mActivityPresenter.showTripDetailsView(tripId);
//
//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container, tripDetailsFragment)
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    public void showCreateTrip() {

        mActivityPresenter.showCreateTrip();

//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container, createEditTripFragment)
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    public void showNoTrips() {
        showNoTripsView("You don't have any trip yet!", R.drawable.ic_basket_black_24dp, true);
    }

    @Override
    public void showLoadingTripsError() {
        showMessage("Error cargando tus viajes!");
    }

    @Override
    public void showSuccessfullySavedTrip() {
        showMessage("Viaje creado con éxito!");
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    private void showMessage(String message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        } else {
            Log.e(TAG, "showMessage: getView() is null");
        }
    }

    private void showNoTripsView(String mainText, int iconRes, boolean showCreateView) {
        mTripsView.setVisibility(View.GONE);
        mNoTripsView.setVisibility(View.VISIBLE);

        mNoTripsMainTextView.setText(mainText);
        mNoTripsIcon.setImageDrawable(getResources().getDrawable(iconRes));
        mNoTripsCreateView.setVisibility(showCreateView ? View.VISIBLE : View.GONE);
    }
}
