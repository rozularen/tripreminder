package com.argandevteam.tripreminder.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.local.TripsPersistenceContract.TaskEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markc on 23/07/2017.
 */

public class TripsLocalDataSource implements TripsDataSource {

    private static TripsLocalDataSource INSTANCE;

    private TripsDbHelper mDbHelper;

    public TripsLocalDataSource(Context context) {
        if (context != null) {
            mDbHelper = new TripsDbHelper(context);
        }
    }

    public static TripsLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TripsLocalDataSource(context);
        }

        return INSTANCE;
    }

    @Override
    public void getTrips(LoadTripsCallback callback) {
        if (callback != null) {
            List<Trip> trips = new ArrayList<Trip>();
            SQLiteDatabase db = mDbHelper.getReadableDatabase();

            String[] projection = {
                    TaskEntry.COLUMN_NAME_TRIP_ID,
                    TaskEntry.COLUMN_NAME_TITLE,
                    TaskEntry.COLUMN_NAME_NUM_PERSONS
            };

            Cursor cursor = db.query(TaskEntry.TABLE_NAME, projection, null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String itemId = cursor.getString(cursor.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_TRIP_ID));
                    //TODO: Get more fields

                    Trip trip = new Trip();
                    trips.add(trip);
                }
            }
            if (cursor != null) {
                cursor.close();
            }

            db.close();

            if (!trips.isEmpty()) {
                callback.onTripsLoaded(trips);
            } else {
                callback.onDataNotAvailable();
            }
        }
    }

    @Override
    public void getTrip(String tripId, GetTripCallback callback) {
        if (tripId != null) {
            if (callback != null) {
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                String[] projection = {
                        TaskEntry.COLUMN_NAME_TRIP_ID,
                        TaskEntry.COLUMN_NAME_TITLE,
                        TaskEntry.COLUMN_NAME_NUM_PERSONS
                };

                String selection = TaskEntry.COLUMN_NAME_TRIP_ID + " LIKE ?";

                String[] selectionArgs = {tripId};

                Cursor cursor = db.query(TaskEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

                Trip trip = null;

                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    String itemId = cursor.getString(cursor.getColumnIndexOrThrow(TaskEntry.COLUMN_NAME_TRIP_ID));
                    //TODO: Get more fields
                }
                if (cursor != null) {
                    cursor.close();
                }
                db.close();
                if (trip != null) {
                    callback.onTripsLoaded(trip);
                } else {
                    callback.onDataNotAvailable();
                }
            }
        }
    }

    @Override
    public void saveTrip(Trip trip) {
        if (trip != null) {

        }
    }

    @Override
    public void deleteTrip(Trip trip) {

    }

    @Override
    public void deleteTrip(String tripId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = TaskEntry.COLUMN_NAME_TRIP_ID + " LIKE ?";
        String[] selectionArgs = { tripId };

        db.delete(TaskEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    @Override
    public void deleteAllTrips() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(TaskEntry.TABLE_NAME, null, null);

        db.close();
    }

    @Override
    public void refreshTrips() {
        //Not required
    }
}
