package com.argandevteam.tripreminder.data.source;

import com.argandevteam.tripreminder.data.Trip;

import java.util.List;

/**
 * Created by markc on 23/07/2017.
 */

public interface TripsDataSource {

    void getTrips(LoadTripsCallback callback);

    void getTrip(String tripId, GetTripCallback callback);

    void saveTrip(Trip trip);

    void deleteTrip(Trip trip);

    void deleteTrip(String tripid);

    void deleteAllTrips();

    void refreshTrips();

    interface LoadTripsCallback {
        void onTripsLoaded(List<Trip> trips);

        void onDataNotAvailable();
    }

    interface GetTripCallback {
        void onTripLoaded(Trip trip);

        void onDataNotAvailable();
    }

}
