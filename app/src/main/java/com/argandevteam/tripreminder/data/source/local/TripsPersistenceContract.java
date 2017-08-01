package com.argandevteam.tripreminder.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by markc on 23/07/2017.
 */

public final class TripsPersistenceContract {

    //To prevent someone instantiating Contract class, give empty constructor
    private TripsPersistenceContract() {
    }

    public class TripEntry implements BaseColumns {
        public static final String TABLE_NAME = "trips";

        public static final String COLUMN_NAME_TRIP_ID = "trip_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_START_DATE = "start_date";
        public static final String COLUMN_NAME_END_DATE = "end_date";
        public static final String COLUMN_NAME_TOTAL_COST = "total_cost";
        public static final String COLUMN_NAME_NUM_PERSONS = "num_persons";
        public static final String COLUMN_NAME_TALK_ID = "talk_id";
        public static final String COLUMN_NAME_TRIP_REMOTE_ID = "remote_id";
    }
}
