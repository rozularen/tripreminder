package com.argandevteam.tripreminder.createedittrip;

import android.text.Editable;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;

import java.util.Date;

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

        void onTripCreated();

        void onTripCreateError();
    }

    interface Presenter extends BasePresenter {
        boolean isDataMissing();

        void saveTrip(String title, String startDate, String endDate, int numPersons, String totalCost);


    }
}
