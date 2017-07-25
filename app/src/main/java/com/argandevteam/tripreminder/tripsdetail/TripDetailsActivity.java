package com.argandevteam.tripreminder.tripsdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.argandevteam.tripreminder.R;

/**
 * Created by markc on 23/07/2017.
 */

public class TripDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TRIP_ID = "TRIP_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        String taskId = getIntent().getStringExtra(EXTRA_TRIP_ID);

    }
}
