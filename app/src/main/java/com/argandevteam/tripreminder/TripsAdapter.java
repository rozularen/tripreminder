package com.argandevteam.tripreminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markc on 21/07/2017.
 */

public class TripsAdapter extends RecyclerView.Adapter {

    private List<Trip> tripsList;

    public TripsAdapter(List<Trip> tripsList) {
        this.tripsList = tripsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View tripCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_card, parent, false);
        // set the view's size, margins, paddings and layout parameters


        TripViewHolder vh = new TripViewHolder(tripCardView);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Trip trip = tripsList.get(position);

        TripViewHolder tripViewHolder = (TripViewHolder) holder;

        tripViewHolder.setData(trip);
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }

    class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textView)
        TextView textView;

        private String name;
        private Trip trip;


        TripViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setData(Trip trip) {
            this.trip = trip;
            textView.setText(trip.getName());
        }

        @Override
        public void onClick(View view) {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment myFragment = new TripDetailsFragment();

            Bundle args = new Bundle();
            args.putParcelable("trip", trip);
            myFragment.setArguments(args);

            FragmentManager supportFragmentManager = activity.getSupportFragmentManager();

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, myFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
