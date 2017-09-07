package com.argandevteam.tripreminder.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by markc on 23/07/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Trips.db";

    private static final String AUTO_INCREMENT = " AUTOINCREMENT";

    private static final String TEXT_TYPE = " TEXT";

    private static final String LONG_TYPE = " BIGINT";

    private static final String DATE_TYPE = " DATE";

    private static final String BOOLEAN_TYPE = " INTEGER",
            INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String PRIMARY_KEY = " PRIMARY KEY";

    private static final String NOT_NULL = " NOT NULL";


    private static final String TRIPS_CREATE_TABLE =
            "CREATE TABLE " + TripsPersistenceContract.TripEntry.TABLE_NAME + " (" +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TRIP_ID + INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + NOT_NULL + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TRIP_REMOTE_ID + INTEGER_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_START_DATE + TEXT_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_END_DATE + TEXT_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_NUM_PERSONS + INTEGER_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TOTAL_COST + TEXT_TYPE + COMMA_SEP +
                    TripsPersistenceContract.TripEntry.COLUMN_NAME_TALK_ID + TEXT_TYPE +
                    " )";

    private static final String ITEMS_CREATE_TABLE =
            "CREATE TABLE " + ItemsPersistenceContract.ItemEntry.TABLE_NAME + " (" +
                    ItemsPersistenceContract.ItemEntry.COLUMN_NAME_ITEM_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
                    ItemsPersistenceContract.ItemEntry.COLUMN_NAME_NAME + TEXT_TYPE +
                    " )";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TRIPS_CREATE_TABLE);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }
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
