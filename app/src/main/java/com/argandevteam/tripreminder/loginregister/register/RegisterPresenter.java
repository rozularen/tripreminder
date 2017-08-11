package com.argandevteam.tripreminder.loginregister.register;

import android.util.Log;

import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;

/**
 * Created by markc on 01/08/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private static final String TAG = "RegisterPresenter";
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

//        AppgreeSDK.API.doRegister(
//                email,
//                firstName,
//                lastName,
//                password,
//                new Callbacks.GenericCallback<AppUser>() {
//                    @Override
//                    public void onSuccess(AppUser appUser) {
//                        Log.d(TAG, "onSuccess: " + appUser.getEmail());
//                        mView.registerSuccess();
//                    }
//
//                    @Override
//                    public void onError(ApiResponseException e, Exception e1) {
//                        Log.e(TAG, "onError: " + e.getMessage() + " " + e.getErrorCode(), e);
//                        Log.e(TAG, "onError: " + e1.getMessage(), e1);
//                        mView.registerError();
//                    }
//                }
//        );
    }
}
