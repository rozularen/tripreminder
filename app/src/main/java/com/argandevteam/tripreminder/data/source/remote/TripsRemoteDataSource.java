package com.argandevteam.tripreminder.data.source.remote;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;

import java.util.Map;

/**
 * Created by markc on 23/07/2017.
 */

public class TripsRemoteDataSource implements TripsDataSource {

    private static TripsRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILIS = 5000;

    private final static Map<String, Trip> TASK_SERVICE_DATA = null;

    //SHould prevent direct instantiation
    public TripsRemoteDataSource() {}

    @Override
    public void getTrips(LoadTripsCallback callback) {

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
    public void refreshTrips() {

    }
}
