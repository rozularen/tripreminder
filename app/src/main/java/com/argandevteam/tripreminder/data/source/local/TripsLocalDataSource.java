package com.argandevteam.tripreminder.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.argandevteam.tripreminder.data.Item;
import com.argandevteam.tripreminder.data.Trip;
import com.argandevteam.tripreminder.data.source.TripsDataSource;
import com.argandevteam.tripreminder.data.source.local.TripsPersistenceContract.TripEntry;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by markc on 23/07/2017.
 */

public class TripsLocalDataSource implements TripsDataSource {

    private static final String TAG = "LocalDataSrc";
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
        Realm realm = Realm.getDefaultInstance();
        RealmResults trips = realm.where(Trip.class).findAll();

        if (callback != null) {
            callback.onTripsLoaded(trips);
        }
    }

    @Override
    public void getTrip(String tripId, GetTripCallback callback) {
        if (callback != null) {
            Realm realm = Realm.getDefaultInstance();
            Trip trip = realm.where(Trip.class).equalTo("id", tripId).findFirst();
            callback.onTripLoaded(trip);

        } else {
            Log.d(TAG, "getTrip: CANT BE NULL!!");
        }

    }

    @Override
    public void saveTrip(Trip trip, SaveTripCallback callback) {
        // Obtain a Realm instance
        if (callback != null) {
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            Trip newTrip = realm.copyToRealm(trip);

            realm.commitTransaction();

            callback.onTripSaved(newTrip);


        } else {
            Log.d(TAG, "saveTrip: Save trip local cant be null");
        }
    }

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
    public void deleteTrip(String tripid) {
        //TODO: COnsider using a callbal

        Realm realm = Realm.getDefaultInstance();

        final RealmResults<Trip> results = realm.where(Trip.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteFirstFromRealm();
            }
        });

    }

    @Override
    public void addItem(String mTripId, final Item newItem, final NewItemCallback callback) {
        getTrip(mTripId, new GetTripCallback() {
            @Override
            public void onTripLoaded(final Trip trip) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {

                    @Override
                    public void execute(Realm realm) {
                        Item item = realm.copyToRealm(newItem);

                        trip.getItemsList().add(newItem);

                        Trip updatedTrip = realm.copyToRealmOrUpdate(trip);

                        callback.onItemCreated(updatedTrip.getItemsList());
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                Log.e(TAG, "onDataNotAvailable: CANT GETTRIP ");
            }
        });

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
