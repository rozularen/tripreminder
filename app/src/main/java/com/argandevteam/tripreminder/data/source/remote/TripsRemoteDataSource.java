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

    private static final int SERVICE_LATENCY_IN_MILIS = 5000;
    private static TripsRemoteDataSource INSTANCE;
    private static Map<String, Trip> TRIP_SERVICE_DATA = null;

    static {
        TRIP_SERVICE_DATA = new LinkedHashMap<>(2);
        addTrip("1", "MALAGA", "1465003330000", "1487491870000", 4, "220");
        addTrip("2", "CADIZ", "1466043360000", "1467243470000", 3, "158");

    }

    //SHould prevent direct instantiation
    public TripsRemoteDataSource() {
    }

    private static void addTrip(String id, String title, String startDate, String endDate, int numPersons, String totalCost) {
        Trip newTrip = new Trip(id, title, startDate, endDate, numPersons, totalCost);
        TRIP_SERVICE_DATA.put(newTrip.getId(), newTrip);
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
    public void getTrip(String tripId, final GetTripCallback callback) {
        final Trip trip = TRIP_SERVICE_DATA.get(tripId);

        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTripsLoaded(trip);
            }
        }, SERVICE_LATENCY_IN_MILIS);
    }

    @Override
    public void saveTrip(Trip trip) {
        TRIP_SERVICE_DATA.put(trip.getId(), trip);
    }

    @Override
    public void deleteTrip(Trip trip) {
        TRIP_SERVICE_DATA.remove(trip.getId());
    }

    @Override
    public void deleteTrip(String tripId) {
        TRIP_SERVICE_DATA.remove(tripId);
    }

    @Override
    public void deleteAllTrips() {
        TRIP_SERVICE_DATA.clear();
    }

    @Override
    public void refreshTrips() {
        // Not required, {@link TripsRepository} handles the logic
        // of refreshing the trips from all the available data
    }
}
