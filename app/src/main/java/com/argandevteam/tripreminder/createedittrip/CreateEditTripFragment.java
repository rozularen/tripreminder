package com.argandevteam.tripreminder.createedittrip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.trips.ActivityContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markc on 25/07/2017.
 */

public class CreateEditTripFragment extends Fragment implements CreateEditTripContract.View {

    @BindView(R.id.trip_title)
    EditText tripTitle;

    private static final String ARGS_TRIP_ID = "TRIP_ID";

    private CreateEditTripContract.Presenter mPresenter;
    private ActivityContract.Presenter mActivityPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_edit_trip, container, false);

        ButterKnife.bind(this, view);

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab_create_trip);
        floatingActionButton.setImageResource(R.drawable.ic_check);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveTrip(tripTitle.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void setPresenter(CreateEditTripContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void setActivityPresenter(ActivityContract.Presenter mActivityPresenter) {
        this.mActivityPresenter = mActivityPresenter;
    }

    public static CreateEditTripFragment newInstance() {
        return new CreateEditTripFragment();
    }


    public static CreateEditTripFragment newInstance(String mTripId) {
        CreateEditTripFragment createEditTripFragment = new CreateEditTripFragment();

        Bundle args = new Bundle();
        args.putString(ARGS_TRIP_ID, mTripId);
        createEditTripFragment.setArguments(args);

        return createEditTripFragment;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setTitle(String title) {
        tripTitle.setText(title);
    }

    @Override
    public void showEmptyTripError() {
        showMessage("Trip Vacio");
    }

    @Override
    public void showTripsList() {

    }

    private void showMessage(String message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        }
    }
}