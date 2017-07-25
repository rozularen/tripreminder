package com.argandevteam.tripreminder.trips;

import com.argandevteam.tripreminder.createedittrip.CreateEditTripFragment;
import com.argandevteam.tripreminder.tripsdetail.TripDetailsFragment;

/**
 * Created by markc on 25/07/2017.
 */

public interface ActivityContract {
    interface View {
        void showTripDetailsView(String tripId);

        void showCreateTrip(String tripId);

        void showEditTrip(String tripId);
    }

    interface Presenter {
        void showTripDetailsView(String tripId);

        void showCreateTrip();

        void showEditTrip(String tripId);
    }

}
