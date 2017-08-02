package com.argandevteam.tripreminder.tripsdetail;

import android.util.Log;

import com.appgree.core.task.ApiResponseException;
import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.TripsRepository;

import java.text.SimpleDateFormat;

/**
 * Created by markc on 23/07/2017.
 */

public class TripDetailsPresenter implements TripDetailsContract.Presenter {

    private static final String TAG = "DetailsPresenter";

    private TripsRepository mTripsRepository = null;
    private TripDetailsContract.View mView = null;
    private String mTripId;

    public TripDetailsPresenter(String mTripId, TripsRepository mTripsRepository, TripDetailsContract.View mView) {
        this.mTripId = mTripId;
        if (mTripsRepository != null) {
            this.mTripsRepository = mTripsRepository;
            if (mView != null) {
                this.mView = mView;
                mView.setPresenter(this);
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
//        checkIfHasTalk();
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

        //TODO:Consider using TextUtils
        if (trip != null) {
            String title = trip.getTitle();
            int numPersons = trip.getNumPersons();
            String startDate = trip.getStartDate();
            String endDate = trip.getEndDate();
            String totalCost = trip.getTotalCost();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            mView.showTrip(title, startDate, endDate, numPersons, totalCost);
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

    private void checkIfHasTalk() {
        mTripsRepository.getTrip(mTripId, new TripsDataSource.GetTripCallback() {
            @Override
            public void onTripLoaded(Trip trip) {
                if (trip.getTalkId() != null) {
                    mView.showTalk();
                } else {
                    mView.showCreateTalk();
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.showMessage("Trip Not Available");
            }
        });
    }

    @Override
    public void navigateToTalk() {
        mView.navigateToTalkView();
    }

    @Override
    public void createTalk() {
        mView.navigateToCreateTalkView();
    }
}
