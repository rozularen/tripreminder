package com.argandevteam.tripreminder.trips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.data.source.local.TripsLocalDataSource;
import com.argandevteam.tripreminder.data.source.remote.TripsRemoteDataSource;
import com.argandevteam.tripreminder.util.ActivityUtils;

public class TripsListActivity extends AppCompatActivity {

    private TripsListPresenter mTripsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);

        TripsListFragment tripsListTripsListFragment =
                (TripsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if(tripsListTripsListFragment == null){
            //Create fragment
            tripsListTripsListFragment = TripsListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    tripsListTripsListFragment, R.id.fragment_container);
        }

        //Should use DI framework
        TripsLocalDataSource mTripsLocalDataSource = new TripsLocalDataSource(this);
        TripsRemoteDataSource mTripsRemoteDataSource = new TripsRemoteDataSource();

        TripsRepository mTripsRepository = new TripsRepository(mTripsLocalDataSource, mTripsRemoteDataSource);

        mTripsListPresenter = new TripsListPresenter(mTripsRepository, tripsListTripsListFragment);

    }
}
