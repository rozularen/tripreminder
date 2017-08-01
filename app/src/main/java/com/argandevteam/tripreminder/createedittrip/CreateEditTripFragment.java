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

import com.argandevteam.tripreminder.MainActivity;
import com.argandevteam.tripreminder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markc on 25/07/2017.
 */

public class CreateEditTripFragment extends Fragment implements CreateEditTripContract.View {

    private static final String ARGS_TRIP_ID = "TRIP_ID";
    @BindView(R.id.trip_title)
    EditText mTripTitle;
    @BindView(R.id.trip_start_date)
    EditText mTripStartDate;
    @BindView(R.id.trip_end_date)
    EditText mTripEndDate;
    @BindView(R.id.trip_num_persons)
    EditText mTripNumPersons;
    @BindView(R.id.trip_total_cost)
    EditText mTripTotalCost;
    private CreateEditTripContract.Presenter mPresenter;

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
                //TODO: Handle time coming as String but formated as a date

                mPresenter.saveTrip(
                        mTripTitle.getText().toString(),
                        mTripStartDate.getText().toString(),
                        mTripEndDate.getText().toString(),
                        Integer.parseInt(mTripNumPersons.getText().toString()),
                        mTripTotalCost.getText().toString()
                );
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
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setTitle(String title) {
        mTripTitle.setText(title);
    }

    @Override
    public void showEmptyTripError() {
        showMessage("Trip Vacio");
    }

    @Override
    public void showTripsList() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showTripsList();

        showMessage("Trip creado con exito");
    }

    @Override
    public void setStartDate(String startDate) {
        mTripStartDate.setText(startDate);
    }

    @Override
    public void setEndDate(String endDate) {
        mTripEndDate.setText(endDate);
    }

    @Override
    public void setNumPersons(String numPersons) {
        mTripNumPersons.setText(numPersons);
    }

    @Override
    public void setTotalCost(String totalCost) {
        mTripTotalCost.setText(totalCost);
    }

    @Override
    public void onTripCreated() {
        showMessage("Trip creado con exito");
    }

    @Override
    public void onTripCreateError() {
        showMessage("No se ha podido crear el Trip");
    }

    private void showMessage(String message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
        }
    }
}
