package com.argandevteam.tripreminder.createtalk;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;

/**
 * Created by markc on 01/08/2017.
 */

public interface CreateTalkContract {
    interface View extends BaseView<CreateTalkContract.Presenter> {

        void showTitleIsRequired();

        void navigateToTalk(String talkId);

        void showCreateTalkError(String message);

        void showUserNotLogged();
    }


    interface Presenter extends BasePresenter {

        void createTalk(String title, String description);
    }
}
