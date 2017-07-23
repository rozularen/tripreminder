package com.argandevteam.tripreminder.trips;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;
import com.argandevteam.tripreminder.data.Trip;

import java.util.List;

/**
 * Created by markc on 23/07/2017.
 */

public interface TripsListContract {

    interface View extends BaseView<Presenter> {

        void setLoadingView(boolean showLoadingView);

        void showTrips(List<Trip> tripsToShow);

        void showTripDetailsView(String tripId);

        void showCreateTrip();

        void showNoTrips();

        void showLoadingTripsError();

        void showSuccessfullySavedTrip();





        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadTrips(boolean forceUpdate);

        void loadTrips(boolean forceUpdate, boolean showLoadingView);

        void openTripDetails(Trip requestedTrip);

        void createTrip();

        void deleteTrip(Trip trip);
    }
}
