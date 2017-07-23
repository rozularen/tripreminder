package com.argandevteam.tripreminder.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by markc on 23/07/2017.
 */

public final class TripsPersistenceContract {

    //To prevent someone instantiating Contract class, give empty constructor
    private TripsPersistenceContract() {
    }

    public class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "trips";
        public static final String COLUMN_NAME_TRIP_ID = "tripId";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_NUM_PERSONS = "num_persons";
    }
}
