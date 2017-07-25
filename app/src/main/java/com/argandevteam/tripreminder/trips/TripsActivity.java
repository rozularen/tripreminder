package com.argandevteam.tripreminder.trips;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.data.source.local.TripsLocalDataSource;
import com.argandevteam.tripreminder.data.source.remote.TripsRemoteDataSource;
import com.argandevteam.tripreminder.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripsActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TripsPresenter mTripsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        ButterKnife.bind(this);

        //Set up the Toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Set up the Drawer Layout
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        if (navigationView != null) {
            setUpDrawerContent(navigationView);
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Open DrawerLayout when Home is clicked
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.buenas:
                        break;
                    case R.id.Hola:
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();

                return false;
            }
        });
    }
}
