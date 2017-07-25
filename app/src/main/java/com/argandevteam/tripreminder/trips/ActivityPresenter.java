package com.argandevteam.tripreminder.trips;

import com.argandevteam.tripreminder.tripsdetail.TripDetailsFragment;

/**
 * Created by markc on 25/07/2017.
 */

public class ActivityPresenter implements ActivityContract.Presenter {

    private ActivityContract.View mView;

    public ActivityPresenter(ActivityContract.View mView) {
        if (mView != null) {
            this.mView = mView;
        }
    }

    @Override
    public void showTripDetailsView(String mTripId) {
        mView.showTripDetailsView(mTripId);
    }

    @Override
    public void showCreateTrip() {
        mView.showCreateTrip(null);
    }

    @Override
    public void showEditTrip(String tripId) {
        mView.showEditTrip(tripId);
    }
}