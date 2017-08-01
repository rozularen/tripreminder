package com.argandevteam.tripreminder.loginregister.login;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;
import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;

/**
 * Created by markc on 01/08/2017.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void setActivityPresenter(LoginRegisterContract.Presenter activityPresenter);

        void loginSuccess();
    }

    interface Presenter extends BasePresenter {
        void doLogin(String email, String password);

    }
}
