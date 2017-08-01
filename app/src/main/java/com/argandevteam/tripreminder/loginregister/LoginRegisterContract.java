package com.argandevteam.tripreminder.loginregister;

import com.argandevteam.tripreminder.BaseActivityContract;

/**
 * Created by markc on 01/08/2017.
 */

public interface LoginRegisterContract extends BaseActivityContract {
    interface View {

        void showRegisterView();

        void showMainActivity();
    }

    interface Presenter {

        void showRegisterView();

        void showMainActivity();
    }
}
