package com.argandevteam.tripreminder.data.source;

import com.argandevteam.tripreminder.data.Trip;

import java.util.Map;


/**
 * Created by markc on 23/07/2017.
 */

public class TripsRepository implements TripsDataSource {

    private static TripsRepository INSTANCE = null;

    private TripsDataSource mTripsLocalDataSource = null;

    private TripsDataSource mTripsRemoteDataSource = null;

    Map<String, Trip> mCachedTrips;

    boolean mCacheIsDirty = false;


    public TripsRepository(TripsDataSource mTripsLocalDataSource, TripsDataSource mTripsRemoteDataSource) {
        if (mTripsLocalDataSource != null) {
            this.mTripsLocalDataSource = mTripsLocalDataSource;
        }
        if (mTripsRemoteDataSource != null) {
            this.mTripsRemoteDataSource = mTripsRemoteDataSource;
        }
    }

    public static TripsRepository getInstance(TripsDataSource mTripsLocalDataSource, TripsDataSource mTripsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TripsRepository(mTripsLocalDataSource, mTripsRemoteDataSource);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
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
    public void deleteTrip(String tripId) {
        if (tripId != null) {
            deleteTrip(getTripWithId(tripId));
        }
    }

    private Trip getTripWithId(String tripId) {
        if (tripId != null) {
            if (mCachedTrips == null || mCachedTrips.isEmpty()) {
                return null;
            } else {
                return mCachedTrips.get(tripId);
            }
        }
        return null;
    }

    @Override
    public void refreshTrips() {
        mCacheIsDirty = true;
    }


}
