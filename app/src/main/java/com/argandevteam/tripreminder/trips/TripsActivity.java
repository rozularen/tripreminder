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
import com.argandevteam.tripreminder.createedittrip.CreateEditTripFragment;
import com.argandevteam.tripreminder.createedittrip.CreateEditTripPresenter;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.data.source.local.TripsLocalDataSource;
import com.argandevteam.tripreminder.data.source.remote.TripsRemoteDataSource;
import com.argandevteam.tripreminder.tripsdetail.TripDetailsFragment;
import com.argandevteam.tripreminder.tripsdetail.TripDetailsPresenter;
import com.argandevteam.tripreminder.util.ActivityUtils;
import com.idescout.sql.SqlScoutServer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripsActivity extends AppCompatActivity implements ActivityContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TripsPresenter mTripsPresenter;
    private ActivityContract.Presenter mPresenter;
    private TripsRepository mTripsRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        ButterKnife.bind(this);

        SqlScoutServer.create(this, getPackageName());

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
            tripsFragment = TripsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    tripsFragment, R.id.fragment_container);
        }

        //Should use DI framework
        TripsLocalDataSource mTripsLocalDataSource = new TripsLocalDataSource(this);
        TripsRemoteDataSource mTripsRemoteDataSource = new TripsRemoteDataSource();

        mTripsRepository = new TripsRepository(mTripsLocalDataSource, mTripsRemoteDataSource);

        mPresenter = new ActivityPresenter(this);
        mTripsPresenter = new TripsPresenter(mTripsRepository, tripsFragment, mPresenter);

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

    @Override
    public void showTripDetailsView(String mTripId) {
        TripDetailsFragment fragment = TripDetailsFragment.newInstance(mTripId);

        ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.fragment_container, true);

        new TripDetailsPresenter(
                mTripId,
                mTripsRepository,
                fragment,
                mPresenter);
    }

    @Override
    public void showCreateTrip(String tripId) {
        CreateEditTripFragment fragment = null;
        if (tripId != null) {
            fragment = CreateEditTripFragment.newInstance(tripId);
        } else {
            fragment = CreateEditTripFragment.newInstance();
        }
        ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.fragment_container, true);

        //TODO: Should load boolean from savedInstance

        new CreateEditTripPresenter(
                tripId,
                mTripsRepository,
                fragment,
                true,
                mPresenter);
    }

    @Override
    public void showEditTrip(String tripId) {
        CreateEditTripFragment fragment = null;

        if (tripId != null) {
            fragment = CreateEditTripFragment.newInstance(tripId);
        } else {
            fragment = CreateEditTripFragment.newInstance();
        }

        ActivityUtils.replaceFragment(getSupportFragmentManager(), fragment, R.id.fragment_container, true);

        new CreateEditTripPresenter(
                tripId,
                mTripsRepository,
                fragment,
                true,
                mPresenter);
    }

    @Override
    public void showTripsList() {
        TripsFragment tripsFragment = TripsFragment.newInstance();
        ActivityUtils.replaceFragment(getSupportFragmentManager(), tripsFragment, R.id.fragment_container, false);
        new TripsPresenter(
                mTripsRepository,
                tripsFragment,
                mPresenter
        );

    }
}
