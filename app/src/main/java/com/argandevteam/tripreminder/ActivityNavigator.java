package com.argandevteam.tripreminder;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by markc on 01/08/2017.
 */

public class ActivityNavigator {
//    private Context context;
//
//    public ActivityNavigator(Context context) {
//        this.context = context;
//    }

    public void navigateTo(Activity from, Class to) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
    }
}
