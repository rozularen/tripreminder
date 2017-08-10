package com.argandevteam.tripreminder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.argandevteam.tripreminder.createedittrip.CreateEditTripFragment;
import com.argandevteam.tripreminder.createedittrip.CreateEditTripPresenter;
import com.argandevteam.tripreminder.createtalk.CreateTalkFragment;
import com.argandevteam.tripreminder.createtalk.CreateTalkPresenter;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.data.source.local.TripsLocalDataSource;
import com.argandevteam.tripreminder.data.source.remote.TripsRemoteDataSource;
import com.argandevteam.tripreminder.loginregister.LoginRegisterActivity;
import com.argandevteam.tripreminder.trips.TripsFragment;
import com.argandevteam.tripreminder.trips.TripsPresenter;
import com.argandevteam.tripreminder.tripsdetail.TripDetailsFragment;
import com.argandevteam.tripreminder.tripsdetail.TripDetailsPresenter;
import com.argandevteam.tripreminder.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        TripDetailsFragment.OnTripDetailFragmentListener,
        TripsFragment.OnTripListFragmentListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.backdrop)
    android.widget.ImageView backdrop;


    private TripsPresenter mTripsPresenter;
    private TripsRepository mTripsRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        ButterKnife.bind(this);

        //Set up Appgree SDK
//        AppgreeSDK.setDebugMode(true);

        //Set up the Toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Set up the Drawer Layout
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        if (navigationView != null) {
            setUpDrawerContent(navigationView);
        }

        loadBackdrop();

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

        mTripsPresenter = new TripsPresenter(mTripsRepository, tripsFragment);

    }

    private void loadBackdrop() {
        backdrop.setImageResource(R.drawable.ic_basket_black_24dp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        syncFrags();
    }

    private void syncFrags() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment instanceof TripsFragment) {
            disableCollapse();
        } else if (fragment instanceof TripDetailsFragment) {
            enableCollapse();
        }
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
                    case R.id.trips_list:
                        showTripsList();
                        break;
                    case R.id.user_profile:
                        //TODO: User profile
//                        showUserProfile();
                        break;
                    case R.id.user_logout:
                        //Do logout
                        doLogout();
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

    private void doLogout() {
//        AppgreeSDK.API.doLogout(new Callbacks.GenericCallback<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                showLoginView();
//            }
//
//            @Override
//            public void onError(ApiResponseException e, Exception e1) {
//                //TODO: Eror while doing logout
//            }
//        });

        showLoginView();
    }

    private void showLoginView() {
        ActivityNavigator activityNavigator = new ActivityNavigator();
        activityNavigator.navigateTo(this, LoginRegisterActivity.class, true);
    }

    public void showTripDetailsView(String mTripId) {
        TripDetailsFragment fragment = TripDetailsFragment.newInstance(mTripId);

        ActivityUtils.replaceFragment(
                getSupportFragmentManager(),
                fragment,
                R.id.fragment_container,
                true);

        new TripDetailsPresenter(
                mTripId,
                mTripsRepository,
                fragment);
    }

    public void showCreateTrip(String tripId) {
        CreateEditTripFragment fragment = null;
        if (tripId != null) {
            fragment = CreateEditTripFragment.newInstance(tripId);
        } else {
            fragment = CreateEditTripFragment.newInstance();
        }

        ActivityUtils.replaceFragment(
                getSupportFragmentManager(),
                fragment,
                R.id.fragment_container,
                true);

        //TODO: Should load boolean from savedInstance

        new CreateEditTripPresenter(
                tripId,
                mTripsRepository,
                fragment,
                true);
    }

    public void showEditTrip(String tripId) {
        CreateEditTripFragment fragment = null;

        if (tripId != null) {
            fragment = CreateEditTripFragment.newInstance(tripId);
        } else {
            fragment = CreateEditTripFragment.newInstance();
        }

        ActivityUtils.replaceFragment(
                getSupportFragmentManager(),
                fragment,
                R.id.fragment_container,
                true);

        new CreateEditTripPresenter(
                tripId,
                mTripsRepository,
                fragment,
                true);
    }

    public void showTripsList() {
        TripsFragment tripsFragment = TripsFragment.newInstance();

        ActivityUtils.replaceFragment(
                getSupportFragmentManager(),
                tripsFragment,
                R.id.fragment_container,
                false);

        new TripsPresenter(
                mTripsRepository,
                tripsFragment
        );
    }

    public void showCreateTalkView() {
        CreateTalkFragment createTalkFragment = CreateTalkFragment.newInstance();

        ActivityUtils.replaceFragment(
                getSupportFragmentManager(),
                createTalkFragment,
                R.id.fragment_container,
                false);

        new CreateTalkPresenter(
                mTripsRepository,
                createTalkFragment
        );
    }

    public void showTalkView(String talkId) {
//        try {
//            AppgreeSDK.ViewLoader.loadTalk(this, talkId, new TalkView.Delegate());
//        } catch (AppgreeNotInitializeException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void enableCollapse() {
        backdrop.setVisibility(View.VISIBLE);

        collapsingToolbarLayout.setTitleEnabled(true);
    }

    @Override
    public void disableCollapse() {
        backdrop.setVisibility(View.GONE);
        collapsingToolbarLayout.setTitleEnabled(false);
    }

    public CollapsingToolbarLayout getCollapsingToolbar() {
        return collapsingToolbarLayout;
    }
}
