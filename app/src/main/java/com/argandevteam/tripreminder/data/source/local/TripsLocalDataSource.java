package com.argandevteam.tripreminder.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.local.TripsPersistenceContract.TripEntry;
import com.argandevteam.tripreminder.util.Utils;

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
                    TripEntry.COLUMN_NAME_TRIP_ID,
                    TripEntry.COLUMN_NAME_TRIP_REMOTE_ID,
                    TripEntry.COLUMN_NAME_TITLE,
                    TripEntry.COLUMN_NAME_START_DATE,
                    TripEntry.COLUMN_NAME_END_DATE,
                    TripEntry.COLUMN_NAME_NUM_PERSONS,
                    TripEntry.COLUMN_NAME_TOTAL_COST
            };

            Cursor cursor = db.query(TripEntry.TABLE_NAME, projection, null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_TRIP_ID));
                    long remoteId = cursor.getLong(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_TRIP_REMOTE_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_TITLE));
                    long startDate = cursor.getLong(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_START_DATE));
                    long endDate = cursor.getLong(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_END_DATE));
                    int numPersons = cursor.getInt(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_NUM_PERSONS));
                    String totalCost = cursor.getString(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_TOTAL_COST));

                    Trip trip = new Trip(itemId, title, Utils.fromMillisToText(startDate), Utils.fromMillisToText(endDate), numPersons, totalCost);
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
                        TripEntry.COLUMN_NAME_TRIP_ID,
                        TripEntry.COLUMN_NAME_TRIP_REMOTE_ID,
                        TripEntry.COLUMN_NAME_TITLE,
                        TripEntry.COLUMN_NAME_START_DATE,
                        TripEntry.COLUMN_NAME_END_DATE,
                        TripEntry.COLUMN_NAME_NUM_PERSONS,
                        TripEntry.COLUMN_NAME_TOTAL_COST
                };

                String selection = TripEntry.COLUMN_NAME_TRIP_ID + " LIKE ?";

                String[] selectionArgs = {tripId};

                Cursor cursor = db.query(TripEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

                Trip trip = null;

                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_TRIP_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_TITLE));
                    String startDate = cursor.getString(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_START_DATE));
                    String endDate = cursor.getString(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_END_DATE));
                    int numPersons = cursor.getInt(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_NUM_PERSONS));
                    String totalCost = cursor.getString(cursor.getColumnIndexOrThrow(TripEntry.COLUMN_NAME_TOTAL_COST));

                    //TODO: Fetch items associated
                    trip = new Trip(id, title, startDate, endDate, numPersons, totalCost);
                }
                if (cursor != null) {
                    cursor.close();
                }
                db.close();
                if (trip != null) {
                    callback.onTripLoaded(trip);
                } else {
                    callback.onDataNotAvailable();
                }
            }
        }
    }

    @Override
    public void saveTrip(Trip trip) {
        if (trip != null) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(TripEntry.COLUMN_NAME_TITLE, trip.getTitle());
            contentValues.put(TripEntry.COLUMN_NAME_TRIP_REMOTE_ID, trip.getRemoteId());
            contentValues.put(TripEntry.COLUMN_NAME_START_DATE, trip.getStartDate());
            contentValues.put(TripEntry.COLUMN_NAME_END_DATE, trip.getEndDate());
            contentValues.put(TripEntry.COLUMN_NAME_NUM_PERSONS, trip.getNumPersons());
            contentValues.put(TripEntry.COLUMN_NAME_TOTAL_COST, trip.getTotalCost());

            db.insert(TripEntry.TABLE_NAME, null, contentValues);

            db.close();
        }
    }

    //
//    @Override
//    public void saveTrip(Trip trip, SaveTripCallback callback) {
//        if (trip != null) {
//            SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//            ContentValues contentValues = new ContentValues();
//
//            contentValues.put(TripEntry.COLUMN_NAME_TITLE, trip.getTitle());
//            contentValues.put(TripEntry.COLUMN_NAME_TRIP_REMOTE_ID, trip.getRemoteId());
//            contentValues.put(TripEntry.COLUMN_NAME_START_DATE, trip.getStartDate());
//            contentValues.put(TripEntry.COLUMN_NAME_END_DATE, trip.getEndDate());
//            contentValues.put(TripEntry.COLUMN_NAME_NUM_PERSONS, trip.getNumPersons());
//            contentValues.put(TripEntry.COLUMN_NAME_TOTAL_COST, trip.getTotalCost());
//
//            db.insert(TripEntry.TABLE_NAME, null, contentValues);
////            long tripId = db.insert(TripEntry.TABLE_NAME, null, contentValues);
////            int tripId = (int) db.insert(TripEntry.TABLE_NAME, null, contentValues);
//
////            contentValues.put(TripEntry.COLUMN_NAME_TRIP_ID, tripId);
//
//            db.close();
//
//            callback.onTripSaved(trip);
//
//        } else {
//            callback.onDataNotAvailable();
//        }
//    }

    @Override
    public void updateTrip(Trip trip) {
        if (trip != null) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(TripEntry.COLUMN_NAME_TRIP_ID, trip.getId());
            contentValues.put(TripEntry.COLUMN_NAME_TRIP_REMOTE_ID, trip.getRemoteId());
            contentValues.put(TripEntry.COLUMN_NAME_TITLE, trip.getTitle());
            contentValues.put(TripEntry.COLUMN_NAME_START_DATE, trip.getStartDate());
            contentValues.put(TripEntry.COLUMN_NAME_END_DATE, trip.getEndDate());
            contentValues.put(TripEntry.COLUMN_NAME_NUM_PERSONS, trip.getNumPersons());
            contentValues.put(TripEntry.COLUMN_NAME_TOTAL_COST, trip.getTotalCost());

            String whereClause = TripEntry.COLUMN_NAME_TRIP_ID + "=?";
            String[] args = new String[]{String.valueOf(trip.getId())};

            db.update(TripEntry.TABLE_NAME, contentValues, whereClause, args);

            db.close();
        }
    }

    @Override
    public void deleteTrip(Trip trip) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = TripEntry.COLUMN_NAME_TRIP_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(trip.getId())};

        db.delete(TripEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    @Override
    public void deleteTrip(String tripId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = TripEntry.COLUMN_NAME_TRIP_ID + " LIKE ?";
        String[] selectionArgs = {tripId};

        db.delete(TripEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    @Override
    public void deleteAllTrips() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(TripEntry.TABLE_NAME, null, null);

        db.close();
    }

    @Override
    public void refreshTrips() {
        //Not required
    }
}
