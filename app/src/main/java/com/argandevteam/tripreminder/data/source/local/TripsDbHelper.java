package com.argandevteam.tripreminder.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by markc on 23/07/2017.
 */

public class TripsDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Trips.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER",
            INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TripsPersistenceContract.TripEntry.TABLE_NAME + " (" +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TRIP_ID + TEXT_TYPE + " PRIMARY KEY," +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_START_DATE + TEXT_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_END_DATE + TEXT_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_NUM_PERSONS + INTEGER_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TOTAL_COST + TEXT_TYPE +
                    " )";

    public TripsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Not required as at version 1
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Not required as at version 1
    }
}
