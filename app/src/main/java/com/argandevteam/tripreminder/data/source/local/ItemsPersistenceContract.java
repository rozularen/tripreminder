package com.argandevteam.tripreminder.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by markc on 28/07/2017.
 */

public class ItemsPersistenceContract {
    private ItemsPersistenceContract() {
    }

    public class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME_ITEM_ID = "item_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_QUANTITY = "quantity";

    }
}
