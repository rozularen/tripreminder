package com.argandevteam.tripreminder.data.source.local;

import android.content.Context;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;

/**
 * Created by markc on 23/07/2017.
 */

public class TripsLocalDataSource implements TripsDataSource {

    private static TripsLocalDataSource INSTANCE;

    private TripsDbHelper mDbHelper;

    public TripsLocalDataSource(Context context){
        if(context != null){
            mDbHelper = new TripsDbHelper(context);
        }
    }

    public static TripsLocalDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new TripsLocalDataSource(context);
        }

        return INSTANCE;
    }

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
