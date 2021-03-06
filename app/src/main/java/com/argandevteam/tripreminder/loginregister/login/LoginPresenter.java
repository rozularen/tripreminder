package com.argandevteam.tripreminder.loginregister.login;


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
//        if (AppgreeSDK.isStarted()) {
//            // TODO: Do login
//            //Login normally, first with appgree then locally/remotely
//            AppgreeSDK.API.doLogin(email, password, new Callbacks.GenericCallback<AppUser>() {
//                @Override
//                public void onSuccess(AppUser appUser) {
//                    mView.loginSuccess();
//                }
//
//                @Override
//                public void onError(ApiResponseException e, Exception e1) {
//                    mView.loginError();
//                }
//            });
//        } else {
//            // TODO: Do login
//            //If appgree is not started, do just login
//        }
        mView.loginSuccess();

    }

}
