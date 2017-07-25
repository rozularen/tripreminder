package com.argandevteam.tripreminder.tripsdetail;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;
import com.argandevteam.tripreminder.data.Trip;

/**
 * Created by markc on 23/07/2017.
 */

public interface TripDetailsContract {

    interface View extends BaseView<Presenter> {

        void showEditTrip(String tripId);

        void showMissingTrip();

        boolean isActive();

        void showTitle(String title);

        void showTripDeleted();
    }

    interface Presenter extends BasePresenter {

        void openTrip();

        void editTrip();

        void deleteTrip();
    }
}
