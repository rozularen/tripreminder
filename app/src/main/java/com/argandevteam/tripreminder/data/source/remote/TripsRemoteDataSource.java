package com.argandevteam.tripreminder.data.source.remote;

import android.os.Handler;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by markc on 23/07/2017.
 */

public class TripsRemoteDataSource implements TripsDataSource {

    private static TripsRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILIS = 5000;

    private static Map<String, Trip> TRIP_SERVICE_DATA = null;

    static {
        TRIP_SERVICE_DATA = new LinkedHashMap<>(2);
        addTrip("ALVAA", "asda");
        addTrip("MALAGA", "DESCRIPCION");

    }

    private static void addTrip(String name, String description) {
        Trip newTrip = new Trip(name, description);
        TRIP_SERVICE_DATA.put(newTrip.getId(), newTrip);
    }

    //SHould prevent direct instantiation
    public TripsRemoteDataSource() {
    }

    @Override
    public void getTrips(final LoadTripsCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Trip> tripsList = new ArrayList<>(TRIP_SERVICE_DATA.values());
                callback.onTripsLoaded(tripsList);
            }
        }, SERVICE_LATENCY_IN_MILIS);
    }

    @Override
    public void getTrip(String tripId, GetTripCallback callback) {

    }

    @Override
    public void saveTrip(Trip trip) {

    }

    @Override
    public void deleteTrip(Trip trip) {

    }

    @Override
    public void deleteTrip(String tripid) {

    }

    @Override
    public void deleteAllTrips() {

    }

    @Override
    public void refreshTrips() {

    }
}
