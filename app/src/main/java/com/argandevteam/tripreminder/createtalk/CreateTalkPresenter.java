package com.argandevteam.tripreminder.createtalk;

import com.appgree.core.rest.model.LoginData;
import com.appgree.core.rest.model.Talk;
import com.appgree.core.task.ApiResponseException;
import com.appgree.sdk.AppgreeSDK;
import com.appgree.sdk.Callbacks;
import com.argandevteam.tripreminder.createtalk.CreateTalkContract.Presenter;
import com.argandevteam.tripreminder.data.source.TripsRepository;
import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;

/**
 * Created by markc on 01/08/2017.
 */

public class CreateTalkPresenter implements Presenter {

    private static final String TAG = "CreateTalk";

    private TripsRepository mTripsRepository;
    private CreateTalkContract.View mView;
    private LoginRegisterContract.Presenter mActivityPresenter;

    public CreateTalkPresenter(TripsRepository tripsRepository, CreateTalkContract.View view) {
        if (tripsRepository != null) {
            mTripsRepository = tripsRepository;
            if (view != null) {
                mView = view;

                mView.setPresenter(this);
            }
        }
    }

    @Override
    public void start() {
        checkUserIsLogged();
    }

    private void checkUserIsLogged() {
        AppgreeSDK.API.getUserData(new Callbacks.GenericCallback<LoginData>() {
            @Override
            public void onSuccess(LoginData loginData) {

            }

            @Override
            public void onError(ApiResponseException e, Exception e1) {
                if (e != null) {
                    mView.showCreateTalkError(e.getMessage());
                } else {
                    mView.showCreateTalkError(e1.getMessage());
                }
                mView.showUserNotLogged();
            }
        });
    }

    @Override
    public void createTalk(String title, String description) {
        Talk talkData = new Talk();

        if (title != null && !title.equals("")) {
            talkData.setTitle(title);
        } else {
            // TODO: show error message on talk
            mView.showTitleIsRequired();
        }

        if (description != null && !description.equals("")) {
            talkData.setDescription(description);
        }

        AppgreeSDK.API.createTalk(talkData, new Callbacks.GenericCallback<Talk>() {
            @Override
            public void onSuccess(Talk talk) {
                mView.navigateToTalk(talk.getTalkId());
            }

            @Override
            public void onError(ApiResponseException e, Exception e1) {
                if (e != null) {
                    mView.showCreateTalkError(e.getMessage());
                } else {
                    mView.showCreateTalkError(e1.getMessage());
                }
            }
        });
    }
}
