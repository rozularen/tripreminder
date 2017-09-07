package com.argandevteam.tripreminder.data.source.local;

import android.content.Context;

import com.argandevteam.tripreminder.data.User;
import com.argandevteam.tripreminder.data.source.UsersDataSource;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by markc on 16/08/2017.
 */

public class UsersLocalDataSource implements UsersDataSource {

    private static UsersLocalDataSource INSTANCE;
    private DbHelper mDbHelper;

    public UsersLocalDataSource(Context context) {
        if (context != null) {
            mDbHelper = new DbHelper(context);
        }
    }

    private static UsersLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UsersLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getUser(String userId) {
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).equalTo("userId", userId).findFirst();

    }

    @Override
    public void saveUser(User user) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        User newUser = realm.copyToRealm(user);

        realm.commitTransaction();

//        callback.onTripSaved(newTrip);
    }

    @Override
    public void editUser(User user) {

    }
}
