package com.argandevteam.tripreminder.data.source;

import com.argandevteam.tripreminder.data.Item;
import com.argandevteam.tripreminder.data.Trip;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by markc on 23/07/2017.
 */

public interface TripsDataSource {

    void getTrips(LoadTripsCallback callback);

    void getTrip(String tripId, GetTripCallback callback);

    void saveTrip(Trip trip, SaveTripCallback callback);

    void updateTrip(Trip trip);

    void deleteTrip(Trip trip);

    void deleteTrip(String tripid);

    void deleteAllTrips();

    void refreshTrips();

    void addItem(String mTripId, Item newItem, NewItemCallback callback);

    interface SaveTripCallback {
        void onTripSaved(Trip trip);

        void onDataNotAvailable();
    }

    interface LoadTripsCallback {
        void onTripsLoaded(List<Trip> trips);

        void onDataNotAvailable();
    }

    interface GetTripCallback {
        void onTripLoaded(Trip trip);

        void onDataNotAvailable();
    }

    public interface NewItemCallback {
        void onItemCreated(RealmList<Item> itemsList);

        void onError();
    }
}
