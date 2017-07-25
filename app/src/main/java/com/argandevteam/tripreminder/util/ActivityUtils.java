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

    public static void replaceFragment(FragmentManager fragmentManager,
                                       Fragment fragment, int frameId, boolean addToBackStack) {
        if (fragmentManager != null) {
            if (fragment != null) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(frameId, fragment);
                if (addToBackStack) {
                    transaction.addToBackStack(null);
                }
                transaction.commit();
            }
        }
    }
}
