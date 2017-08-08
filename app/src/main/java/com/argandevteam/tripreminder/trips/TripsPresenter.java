package com.argandevteam.tripreminder.trips;

import android.util.Log;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.TripsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markc on 21/07/2017.
 */

public class TripsPresenter implements TripsContract.Presenter {

    private static final String TAG = "TripsPresenter";

    private TripsRepository mTripsRepository = null;
    private TripsContract.View mView = null;

    private boolean mFirstLoad = true;

    public TripsPresenter(TripsRepository mTripsRepository, TripsContract.View mView) {
        if (mTripsRepository != null) {
            this.mTripsRepository = mTripsRepository;
            if (mView != null) {
                this.mView = mView;
                mView.setPresenter(this);
            } else {
                Log.e(TAG, "Presenter: View can't be null");
            }
        } else {
            Log.e(TAG, "Presenter: TripsRepository can't be null");
        }
    }


    @Override
    public void start() {
        loadTrips(false);
    }

    @Override
    public void loadTrips(boolean forceUpdate) {
//        loadTrips(forceUpdate || mFirstLoad, true);
        loadTrips(forceUpdate, true);
        mFirstLoad = false;
    }

    @Override
    public void loadTrips(boolean forceUpdate, final boolean showLoadingView) {
        if (showLoadingView) {
            mView.setLoadingView(true);
        }
        if (forceUpdate) {
            mTripsRepository.refreshTrips();
        }

        mTripsRepository.getTrips(new TripsDataSource.LoadTripsCallback() {
            @Override
            public void onTripsLoaded(List<Trip> trips) {
                List<Trip> tripsToShow = new ArrayList<>();

                for (Trip trip : trips) {
                    tripsToShow.add(trip);
                }

                if (!mView.isActive()) {
                    return;
                }
                if (showLoadingView) {
                    mView.setLoadingView(false);
                }

                processTrips(tripsToShow);
            }

            @Override
            public void onDataNotAvailable() {
                Log.d(TAG, "onDataNotAvailable: ERROR LOADING TRIP DATA NOT AVAILABLE");
                if (!mView.isActive()) {
                    return;
                }
                mView.showLoadingTripsError();
            }
        });
    }

    private void processTrips(List<Trip> tripsToShow) {
        if (!tripsToShow.isEmpty()) {
            mView.showTrips(tripsToShow);
        } else {
            //Show message indicating there are no Trips
            processEmptyTrips();
        }
    }

    private void processEmptyTrips() {
        mView.showNoTrips();
    }

    @Override
    public void openTripDetails(Trip requestedTrip) {
        if (requestedTrip != null) {

            mView.showTripDetailsView(String.valueOf(requestedTrip.getId()));
        } else {
            Log.e(TAG, "openTripDetails: Requested Trip can't be null");
        }
    }

    @Override
    public void createTrip() {
        mView.showCreateTrip();
    }

    @Override
    public void deleteTrip(Trip deletedTrip) {
        if (deletedTrip != null) {
            mTripsRepository.deleteTrip(deletedTrip);
        }
    }

    @Override
    public void result(int requestCode, int resultCode) {
        mView.showSuccessfullySavedTrip();
    }
}
