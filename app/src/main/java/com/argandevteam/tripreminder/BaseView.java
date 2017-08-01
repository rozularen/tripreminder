package com.argandevteam.tripreminder;


import com.argandevteam.tripreminder.trips.ActivityContract;

/**
 * Created by markc on 23/07/2017.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void setActivityPresenter(ActivityContract.Presenter activityPresenter);

}
