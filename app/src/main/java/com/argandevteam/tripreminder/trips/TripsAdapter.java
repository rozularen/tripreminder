package com.argandevteam.tripreminder.trips;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.data.Trip;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markc on 21/07/2017.
 */

public class TripsAdapter extends RecyclerView.Adapter {

    private List<Trip> tripsList;
    private TripItemListener listener;

    public TripsAdapter(List<Trip> tripsList) {
        this.tripsList = tripsList;
    }

    public TripsAdapter(List<Trip> tripsList, TripItemListener listener) {
        this.tripsList = tripsList;
        this.listener = listener;
    }

    public void replaceData(List<Trip> tripsToShow) {
        tripsList = tripsToShow;

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View tripCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_card, parent, false);
        // set the view's size, margins, paddings and layout parameters

        TripViewHolder vh = new TripViewHolder(tripCardView, listener);
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

    interface TripItemListener {
        void onTripClick(Trip clickedTrip);
    }

    class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.trip_title)
        TextView tripTitle;

        @BindView(R.id.trip_start_date)
        TextView tripStartDate;

        @BindView(R.id.trip_end_date)
        TextView tripEndDate;

        @BindView(R.id.trip_num_persons)
        TextView tripNumPersons;

        @BindView(R.id.trip_total_cost)
        TextView tripTotalCost;

        private Trip trip;
        private TripItemListener listener;

        TripViewHolder(View itemView, TripItemListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void setData(Trip trip) {
            this.trip = trip;
            tripTitle.setText(trip.getTitle());

            String startDate = trip.getStartDate();
            String endDate = trip.getEndDate();

            tripStartDate.setText(trip.getStartDate());
            tripEndDate.setText(trip.getEndDate());

            tripNumPersons.setText(Integer.toString(trip.getNumPersons()));
            tripTotalCost.setText(trip.getTotalCost());
        }

        @Override
        public void onClick(View view) {
            listener.onTripClick(trip);

        }
    }
}
