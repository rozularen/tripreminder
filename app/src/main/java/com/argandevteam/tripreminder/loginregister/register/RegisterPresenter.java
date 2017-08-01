package com.argandevteam.tripreminder.loginregister.register;

import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;

/**
 * Created by markc on 01/08/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mView;
    private LoginRegisterContract.Presenter mActivityPresenter;

    public RegisterPresenter(RegisterContract.View view, LoginRegisterContract.Presenter activityPresenter) {
        mView = view;
        mActivityPresenter = activityPresenter;
        mView.setPresenter(this);
        mView.setActivityPresenter(mActivityPresenter);
    }

    @Override
    public void start() {

    }

    @Override
    public void registerUser(String email, String firstName, String lastName, String password) {
        //Registrer user

        mView.registerSuccess();
    }
}
