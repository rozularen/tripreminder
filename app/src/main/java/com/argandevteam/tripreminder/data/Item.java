package com.argandevteam.tripreminder.data;

import io.realm.RealmObject;

/**
 * Created by markc on 21/07/2017.
 */

public class Item extends RealmObject {
    private String name;
    private String type;
    private int quantity;
}
