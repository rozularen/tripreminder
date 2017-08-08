package com.argandevteam.tripreminder.tripsdetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.argandevteam.tripreminder.MainActivity;
import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.data.Item;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailsFragment extends Fragment implements TripDetailsContract.View, View.OnClickListener {

    FloatingActionMenu mFabMenu;

    @BindView(R.id.trip_title)
    TextView mTripTitle;

    @BindView(R.id.trip_start_date)
    TextView mTripStartDate;

    @BindView(R.id.trip_end_date)
    TextView mTripEndDate;

    @BindView(R.id.trip_num_persons)
    TextView mTripNumPersons;

    @BindView(R.id.trip_total_cost)
    TextView mTripTotalCost;

    @BindView(R.id.no_items_view)
    LinearLayout mNoItemsView;

    @BindView(R.id.items_view)
    LinearLayout mItemsView;

    @BindView(R.id.items_recycler_view)
    RecyclerView mItemsRecyclerView;

    @NonNull
    private static final String ARG_TRIP_ID = "TRIP_ID";
    @NonNull
    private static final int REQUEST_EDIT_TRIP = 1;

    private Menu mMenu;
    private TripDetailsContract.Presenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private ItemsAdapter mAdapter;

    public TripDetailsFragment() {
        // Required empty public constructor
    }

    public static TripDetailsFragment newInstance(@NonNull String tripId) {
        Bundle args = new Bundle();
        args.putString(ARG_TRIP_ID, tripId);
        TripDetailsFragment tripDetailsFragment = new TripDetailsFragment();
        tripDetailsFragment.setArguments(args);
        return tripDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Item> tripsList = new ArrayList<>(0);

        mAdapter = new ItemsAdapter(tripsList);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_details, container, false);

        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        //Set up RecyclerView
        mLayoutManager = new LinearLayoutManager(getActivity());
        mItemsRecyclerView.setHasFixedSize(true);
        mItemsRecyclerView.setLayoutManager(mLayoutManager);
        mItemsRecyclerView.setAdapter(mAdapter);

        //Set up Floating Action Button
        setUpFloatingActionMenu();

        return view;
    }

    private void setUpFloatingActionMenu() {
        mFabMenu = (FloatingActionMenu) getActivity().findViewById(R.id.fab_menu);

        FloatingActionButton mFabCreate =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_create_trip);
        FloatingActionButton mFabEditTrip =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_trip);
        FloatingActionButton mFabDeleteTrip =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_delete_trip);
        FloatingActionButton mFabEditItems =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_items);

        mFabMenu.setVisibility(View.VISIBLE);
        mFabCreate.setVisibility(View.GONE);

        mFabEditTrip.setOnClickListener(this);
        mFabDeleteTrip.setOnClickListener(this);
        mFabEditItems.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_trip:
                mPresenter.deleteTrip();
                return true;
            case R.id.navigate_talk:
                mPresenter.navigateToTalk();
                return true;
            case R.id.create_talk:
                mPresenter.createTalk();
                return true;
            default:
                break;
        }
        return false;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        mMenu = menu;
        inflater.inflate(R.menu.details_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setPresenter(TripDetailsContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void showEditTrip(String tripId) {
        mFabMenu.close(true);
        MainActivity activity = (MainActivity) getActivity();
        activity.showEditTrip(tripId);
    }

    @Override
    public void showMissingTrip() {
        mTripTitle.setText("MISSING DATA");

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showTrip(String title, String startDate, String endDate, int numPersons, String totalCost) {
        mTripTitle.setVisibility(View.VISIBLE);
        mTripStartDate.setVisibility(View.VISIBLE);
        mTripTitle.setText(title);

        mTripStartDate.setText(startDate);
        mTripEndDate.setText(endDate);

        mTripNumPersons.setText(String.valueOf(numPersons));
        mTripTotalCost.setText(totalCost);
    }

    @Override
    public void showTalk() {
        MenuItem navigateItem = mMenu.findItem(R.id.navigate_talk);
        MenuItem createItem = mMenu.findItem(R.id.create_talk);
        navigateItem.setVisible(true);
        createItem.setVisible(false);
    }

    @Override
    public void showCreateTalk() {
        MenuItem navigateItem = mMenu.findItem(R.id.navigate_talk);
        MenuItem createItem = mMenu.findItem(R.id.create_talk);
        navigateItem.setVisible(false);
        createItem.setVisible(true);
    }

    @Override
    public void showTripDeleted() {
        mFabMenu.close(true);
        MainActivity activity = (MainActivity) getActivity();
        activity.showTripsList();
    }

    @Override
    public void showErrorAppgree(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToTalkView() {

    }

    @Override
    public void navigateToCreateTalkView() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showCreateTalkView();
    }

    @Override
    public void editItems() {
        //TODO: Make items list editable
        mFabMenu.close(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_edit_items:
                mPresenter.editItems();
                break;
            case R.id.fab_delete_trip:
                mPresenter.deleteTrip();
                break;
            case R.id.fab_edit_trip:
                mPresenter.editTrip();
                break;
            default:
                break;
        }
    }
}
