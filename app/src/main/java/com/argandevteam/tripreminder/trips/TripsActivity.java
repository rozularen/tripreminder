package com.argandevteam.tripreminder.trips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.data.source.local.TripsLocalDataSource;
import com.argandevteam.tripreminder.data.source.remote.TripsRemoteDataSource;
import com.argandevteam.tripreminder.util.ActivityUtils;

public class TripsActivity extends AppCompatActivity {

    private TripsPresenter mTripsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);

        TripsFragment tripsFragment =
                (TripsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (tripsFragment == null) {
            //Create fragment
            tripsFragment = TripsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    tripsFragment, R.id.fragment_container);
        }

        //Should use DI framework
        TripsLocalDataSource mTripsLocalDataSource = new TripsLocalDataSource(this);
        TripsRemoteDataSource mTripsRemoteDataSource = new TripsRemoteDataSource();

        TripsRepository mTripsRepository = new TripsRepository(mTripsLocalDataSource, mTripsRemoteDataSource);

        mTripsPresenter = new TripsPresenter(mTripsRepository, tripsFragment);

    }
}
