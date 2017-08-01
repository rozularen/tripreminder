package com.argandevteam.tripreminder.tripsdetail;

import com.appgree.core.task.ApiResponseException;
import com.appgree.sdk.Callbacks;
import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;

/**
 * Created by markc on 23/07/2017.
 */

public interface TripDetailsContract {

    interface View extends BaseView<Presenter> {

        void showEditTrip(String tripId);

        void showMissingTrip();

        boolean isActive();

        void showTrip(String title, String startDate, String endDate, int numPersons, String totalCost);

        void showTripDeleted();

        void showErrorAppgree(String message);

        void showMessage(String message);

        void showTalk();

        void showCreateTalk();

        void navigateToTalkView();

        void navigateToCreateTalkView();

    }

    interface Presenter extends BasePresenter, Callbacks.GenericCallback<Void> {

        void openTrip();

        void editTrip();

        void deleteTrip();

        void navigateToTalk();

        void createTalk();

        @Override
        void onSuccess(Void aVoid);

        @Override
        void onError(ApiResponseException e, Exception e1);

    }
}
