package com.argandevteam.tripreminder.tripsdetail;

import android.util.Log;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.trips.ActivityContract;

/**
 * Created by markc on 23/07/2017.
 */

public class TripDetailsPresenter implements TripDetailsContract.Presenter {

    private static final String TAG = "DetailsPresenter";

    private TripsRepository mTripsRepository = null;
    private TripDetailsContract.View mView = null;
    private String mTripId;

    public TripDetailsPresenter(String mTripId, TripsRepository mTripsRepository, TripDetailsContract.View mView, ActivityContract.Presenter activityPresenter) {
        this.mTripId = mTripId;
        if (mTripsRepository != null) {
            this.mTripsRepository = mTripsRepository;
            if (mView != null) {
                this.mView = mView;
                mView.setPresenter(this);
                mView.setActivityPresenter(activityPresenter);
            } else {
                Log.e(TAG, "TripDetailsPresenter: View can't be null");
            }
        } else {
            Log.e(TAG, "TripDetailsPresenter: TripsRepository can't be null");
        }
    }

    @Override
    public void start() {
        openTrip();
    }

    @Override
    public void openTrip() {
        //TODO: Consider using TextUtils
        if (mTripId == null || mTripId.length() == 0 || mTripId.equals("")) {
            mView.showMissingTrip();
            return;
        }

        //Set loading UI
        mTripsRepository.getTrip(mTripId, new TripsDataSource.GetTripCallback() {
            @Override
            public void onTripLoaded(Trip trip) {
                if (!mView.isActive()) {
                    return;
                }

                if (null == trip) {
                    mView.showMissingTrip();
                } else {
                    showTrip(trip);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (!mView.isActive()) {
                    return;
                }
                mView.showMissingTrip();
            }
        });
    }

    private void showTrip(Trip trip) {
        String title = trip.getTitle();
        int numPersons = trip.getNumPersons();
        String startDate = trip.getStartDate();
        String endDate = trip.getEndDate();

        //TODO:Consider using TextUtils
        if (title != null) {
            mView.showTitle(title);
        }
    }

    @Override
    public void editTrip() {
        if (mTripId != null) {
            mView.showEditTrip(mTripId);
        } else {
            Log.e(TAG, "editTrip: mTripId can't be null");
            mView.showMissingTrip();
        }
    }

    @Override
    public void deleteTrip() {
        if (mTripId != null) {
            mTripsRepository.deleteTrip(mTripId);
            mView.showTripDeleted();
        }
    }
}
