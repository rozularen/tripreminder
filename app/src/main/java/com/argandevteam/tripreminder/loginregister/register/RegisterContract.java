package com.argandevteam.tripreminder.loginregister.register;

import com.argandevteam.tripreminder.BasePresenter;
import com.argandevteam.tripreminder.BaseView;

/**
 * Created by markc on 01/08/2017.
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {

        void registerSuccess();

        void registerError();
    }

    interface Presenter extends BasePresenter {

        void registerUser(String email, String firstName, String lastName, String password);

    }
}
