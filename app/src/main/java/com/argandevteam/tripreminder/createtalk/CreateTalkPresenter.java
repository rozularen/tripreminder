package com.argandevteam.tripreminder.createtalk;

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

    }

    @Override
    public void createTalk(String title, String description) {
//        Talk talkData = new Talk();
//
//        if (title != null && !title.equals("")) {
//            talkData.setTitle(title);
//        } else {
//            // TODO: show error message on talk
//            mView.showTitleIsRequired();
//        }
//
//        if (description != null && !description.equals("")) {
//            talkData.setDescription(description);
//        }
    }
}
