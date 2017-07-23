package com.argandevteam.tripreminder.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by markc on 23/07/2017.
 */

public class ActivityUtils {
    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             Fragment fragment, int frameId) {
        if (fragmentManager != null) {
            if (fragment != null) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(frameId, fragment);
                transaction.commit();
            }
        }
    }
}
