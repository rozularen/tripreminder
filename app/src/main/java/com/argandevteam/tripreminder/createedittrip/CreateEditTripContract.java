package com.argandevteam.tripreminder.createedittrip;

import android.text.Editable;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;

/**
 * Created by markc on 25/07/2017.
 */

public interface CreateEditTripContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setTitle(String title);

        void showEmptyTripError();

        void showTripsList();

    }

    interface Presenter extends BasePresenter {
        boolean isDataMissing();

        void saveTrip(String text);
    }
}
