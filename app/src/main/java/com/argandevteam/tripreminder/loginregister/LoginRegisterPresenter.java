package com.argandevteam.tripreminder.loginregister;

/**
 * Created by markc on 01/08/2017.
 */

public class LoginRegisterPresenter implements LoginRegisterContract.Presenter {
    private LoginRegisterContract.View mView;

    //TODO: Activity presenter's constructor should get others parameters

    public LoginRegisterPresenter(LoginRegisterContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void showMainActivity() {
        mView.showMainActivity();
    }

    @Override
    public void showRegisterView() {
        mView.showRegisterView();
    }
}
