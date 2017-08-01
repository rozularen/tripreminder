package com.argandevteam.tripreminder.loginregister.register;

import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;

/**
 * Created by markc on 01/08/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mView;
    private LoginRegisterContract.Presenter mActivityPresenter;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void registerUser(String email, String firstName, String lastName, String password) {
        //Registrer user
        // Proceso input y registro usuario
        mView.registerSuccess();
    }
}
