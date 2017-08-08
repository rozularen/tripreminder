package com.argandevteam.tripreminder.createedittrip;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.TripsRepository;

/**
 * Created by markc on 25/07/2017.
 */

public class CreateEditTripPresenter implements CreateEditTripContract.Presenter {

    private static final String TAG = "CETripPresenter";
    private TripsRepository mTripsRepository;
    private CreateEditTripContract.View mView;
    private String mTripId;
    private boolean mIsDataMissing;

    public CreateEditTripPresenter(String tripId,
                                   TripsRepository mTripsRepository,
                                   CreateEditTripContract.View mView,
                                   boolean shouldLoadDataFromRepo) {
        mTripId = tripId;
        if (mTripsRepository != null) {
            this.mTripsRepository = mTripsRepository;
            mIsDataMissing = shouldLoadDataFromRepo;
            if (mView != null) {
                this.mView = mView;
                mView.setPresenter(this);
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
            //TODO: Type mismatch id is string and integer
            updateTrip(mTripId, title, startDate, endDate, numPersons, totalCost);
        }
    }


    private void updateTrip(String tripId, String title, String startDate, String endDate, int numPersons, String totalCost) {
        if (isNewTrip()) {
            throw new RuntimeException("updateTrip() was called but Trip is new");
        }
        Trip updatedTrip = new Trip(tripId, title, startDate, endDate, numPersons, totalCost);

        mTripsRepository.updateTrip(updatedTrip);
        mView.showTripsList();
    }


    private void createTrip(String title, String startDate, String endDate, int numPersons, String totalCost) {
        Trip newTrip = new Trip(title, startDate, endDate, numPersons, totalCost);

        if (newTrip.getTitle().equals("")) {
            mView.showEmptyTripError();
        } else {
            mTripsRepository.saveTrip(newTrip, new TripsDataSource.SaveTripCallback() {
                @Override
                public void onTripSaved(Trip trip) {
                    mView.onTripCreated();
                }

                @Override
                public void onDataNotAvailable() {
                    mView.onTripCreateError();
                }
            });
            mView.showTripsList();
        }
    }
}
