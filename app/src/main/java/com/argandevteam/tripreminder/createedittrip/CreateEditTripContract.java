package com.argandevteam.tripreminder.createedittrip;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;
import com.argandevteam.tripreminder.trips.ActivityContract;

/**
 * Created by markc on 25/07/2017.
 */

public interface CreateEditTripContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setTitle(String title);

        void showEmptyTripError();

        void showTripsList();

        void setStartDate(String startDate);

        void setEndDate(String endDate);

        void setNumPersons(String numPersons);

        void setTotalCost(String totalCost);

        void setActivityPresenter(ActivityContract.Presenter activityPresenter);

        void onTripCreated();

        void onTripCreateError();
    }

    interface Presenter extends BasePresenter {
        boolean isDataMissing();

        void saveTrip(String title, String startDate, String endDate, int numPersons, String totalCost);


    }
}
