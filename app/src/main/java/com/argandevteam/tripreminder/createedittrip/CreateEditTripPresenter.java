package com.argandevteam.tripreminder.createedittrip;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.trips.ActivityContract;

/**
 * Created by markc on 25/07/2017.
 */

public class CreateEditTripPresenter implements CreateEditTripContract.Presenter {

    private TripsRepository mTripsRepository;
    private CreateEditTripContract.View mView;
    private String mTripId;
    private boolean mIsDataMissing;

    public CreateEditTripPresenter(String tripId, TripsRepository mTripsRepository,
                                   CreateEditTripContract.View mView, boolean shouldLoadDataFromRepo, ActivityContract.Presenter mPresenter) {
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
    public void saveTrip(String title) {
        if (isNewTrip()) {
            createTrip(title);
        } else {
            updateTrip(title);
        }
    }

    private void updateTrip(String title) {
        if (isNewTrip()) {
            throw new RuntimeException("updateTrip() was called but Trip is new");
        }
        Trip updatedTrip = new Trip("id", title, "startdate", "enddate", 4, "totalcost");

        mTripsRepository.saveTrip(updatedTrip);
        mView.showTripsList();
    }

    private void createTrip(String title) {
        Trip newTrip = new Trip("id", title, "startdate", "enddate", 4, "totalcost");

        if (newTrip.isEmpty()) {
            mView.showEmptyTripError();
        } else {
            mTripsRepository.saveTrip(newTrip);
            mView.showTripsList();
        }
    }
}
