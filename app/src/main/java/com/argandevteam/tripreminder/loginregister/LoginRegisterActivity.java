package com.argandevteam.tripreminder.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.loginregister.login.LoginFragment;
import com.argandevteam.tripreminder.loginregister.login.LoginPresenter;
import com.argandevteam.tripreminder.loginregister.register.RegisterFragment;
import com.argandevteam.tripreminder.loginregister.register.RegisterPresenter;
import com.argandevteam.tripreminder.trips.TripsActivity;
import com.argandevteam.tripreminder.util.ActivityUtils;

import butterknife.ButterKnife;

public class LoginRegisterActivity extends AppCompatActivity implements LoginRegisterContract.View {

    private LoginRegisterContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        ButterKnife.bind(this);

        mPresenter = new LoginRegisterPresenter(this);

        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    loginFragment, R.id.fragment_container);
        }
        new LoginPresenter(
                loginFragment,
                mPresenter
        );
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(this, TripsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showRegisterView() {
        RegisterFragment registerFragment = RegisterFragment.newInstance();

        ActivityUtils.replaceFragment(
                getSupportFragmentManager(),
                registerFragment,
                R.id.fragment_container,
                true);

        new RegisterPresenter(
                registerFragment,
                mPresenter
        );
    }
}
