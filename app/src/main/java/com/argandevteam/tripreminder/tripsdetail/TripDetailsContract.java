package com.argandevteam.tripreminder.tripsdetail;

import com.appgree.core.task.ApiResponseException;
import com.appgree.sdk.Callbacks;
import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;
import com.argandevteam.tripreminder.data.Item;

import io.realm.RealmList;

/**
 * Created by markc on 23/07/2017.
 */

public interface TripDetailsContract {

    interface View extends BaseView<Presenter> {

        void showEditTrip(String tripId);

        void showMissingTrip();

        boolean isActive();

        void showTrip(String title, String startDate, String endDate, int numPersons, String totalCost, RealmList<Item> itemsList);

        void showTripDeleted();

        void showErrorAppgree(String message);

        void showMessage(String message);

        void showTalk();

        void showCreateTalk();

        void navigateToTalkView();

        void navigateToCreateTalkView();

        void editItems();

        void showEmptyItems();

        void newItemCreated(RealmList<Item> itemsList);

        void hideNoItemsView();
    }

    interface Presenter extends BasePresenter {

        void openTrip();

        void editTrip();

        void deleteTrip();

        void navigateToTalk();

        void createTalk();

        void editItems();

        void addItem(String itemTitle);
    }
}
