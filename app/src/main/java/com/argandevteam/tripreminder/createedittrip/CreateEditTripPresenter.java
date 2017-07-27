package com.argandevteam.tripreminder.createedittrip;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.trips.ActivityContract;
import com.argandevteam.tripreminder.util.Utils;

import java.util.Date;

/**
 * Created by markc on 25/07/2017.
 */

public class CreateEditTripPresenter implements CreateEditTripContract.Presenter {

    private final ActivityContract.Presenter mActivityPresenter = null;
    private TripsRepository mTripsRepository;
    private CreateEditTripContract.View mView;
    private String mTripId;
    private boolean mIsDataMissing;

    public CreateEditTripPresenter(String tripId,
                                   TripsRepository mTripsRepository,
                                   CreateEditTripContract.View mView,
                                   boolean shouldLoadDataFromRepo,
                                   ActivityContract.Presenter mPresenter) {
        mTripId = tripId;
        if (mTripsRepository != null) {
            this.mTripsRepository = mTripsRepository;
            mIsDataMissing = shouldLoadDataFromRepo;
            if (mView != null) {
                this.mView = mView;
                mView.setPresenter(this);
                mView.setActivityPresenter(mPresenter);
            }
        }
    }

    @Override
    public void start() {
        if (!isNewTrip() && mIsDataMissing) {
            populateTrip();
        }
    }

    private void populateTrip() {
        if (isNewTrip()) {
            throw new RuntimeException("populateTrip() was called but Trip is new");
        } else {
            mTripsRepository.getTrip(mTripId, new TripsDataSource.GetTripCallback() {
                @Override
                public void onTripLoaded(Trip trip) {
                    if (mView.isActive()) {
                        mView.setTitle(trip.getTitle());
                        mView.setStartDate(trip.getStartDate());
                        mView.setEndDate(trip.getEndDate());
                        mView.setNumPersons(String.valueOf(trip.getNumPersons()));
                        mView.setTotalCost(trip.getTotalCost());
                        //TODO: populate trip
                    }
                    mIsDataMissing = false;
                }

                @Override
                public void onDataNotAvailable() {
                    if (mView.isActive()) {
                        mView.showEmptyTripError();
                    }
                }
            });
        }
    }


    private boolean isNewTrip() {
        return mTripId == null;
    }

    @Override
    public boolean isDataMissing() {
        return mIsDataMissing;
    }

    @Override
    public void saveTrip(String title, String startDate, String endDate, int numPersons, String totalCost) {
        if (isNewTrip()) {
            createTrip(title, startDate, endDate, numPersons, totalCost);
        } else {
            updateTrip(title, startDate, endDate, numPersons, totalCost);
        }
    }

    private void updateTrip(String title, String startDate, String endDate, int numPersons, String totalCost) {
        if (isNewTrip()) {
            throw new RuntimeException("updateTrip() was called but Trip is new");
        }
        Trip updatedTrip = new Trip(title, startDate, endDate, numPersons, totalCost);
        mTripsRepository.updateTrip(updatedTrip);
        mView.showTripsList();
    }

    private void createTrip(String title, String startDate, String endDate, int numPersons, String totalCost) {
        Trip newTrip = new Trip(title, startDate, endDate, numPersons, totalCost);

        if (newTrip.isEmpty()) {
            mView.showEmptyTripError();
        } else {
            mTripsRepository.saveTrip(newTrip);
//            mTripsRepository.saveTrip(newTrip, new TripsDataSource.SaveTripCallback() {
//                @Override
//                public void onTripSaved(Trip trip) {
//                    mView.onTripCreated();
//                }
//
//                @Override
//                public void onDataNotAvailable() {
//                    mView.onTripCreateError();
//                }
//            });
            mView.showTripsList();
        }
    }
}
