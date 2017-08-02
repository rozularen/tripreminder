package com.argandevteam.tripreminder.loginregister.login;


import com.appgree.core.AppUser;
import com.appgree.core.task.ApiResponseException;
import com.appgree.sdk.AppgreeSDK;
import com.appgree.sdk.Callbacks;
import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;

/**
 * Created by markc on 01/08/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private LoginRegisterContract.Presenter mActivityPresenter;

    public LoginPresenter(LoginContract.View view, LoginRegisterContract.Presenter activityPresenter) {
        mView = view;
        mActivityPresenter = activityPresenter;

        mView.setPresenter(this);
        mView.setActivityPresenter(activityPresenter);
    }

    @Override
    public void start() {

    }

    @Override
    public void doLogin(String email, String password) {
        // TODO: Do login
        //Query local or remote Database for user etc etc
        // is it good?
        AppgreeSDK.API.doLogin(email, password, new Callbacks.GenericCallback<AppUser>() {
            @Override
            public void onSuccess(AppUser appUser) {
                mView.loginSuccess();
            }

            @Override
            public void onError(ApiResponseException e, Exception e1) {
                mView.loginError();
            }
        });
    }

}
