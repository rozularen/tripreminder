package com.argandevteam.tripreminder.loginregister.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.argandevteam.tripreminder.ActivityNavigator;
import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;
import com.argandevteam.tripreminder.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.user_email)
    EditText userEmail;

    @BindView(R.id.user_password)
    EditText userPassword;

    @BindView(R.id.login_user)
    Button loginUser;

    @BindView(R.id.register_link)
    TextView registerLink;

    private LoginContract.Presenter mPresenter;
    private LoginRegisterContract.Presenter mActivityPresenter;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        loginUser.setOnClickListener(this);
        registerLink.setOnClickListener(this);
        return view;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setActivityPresenter(LoginRegisterContract.Presenter activityPresenter) {
        mActivityPresenter = activityPresenter;
    }

    @Override
    public void loginSuccess() {
        ActivityNavigator activityNavigator = new ActivityNavigator();
        activityNavigator.navigateTo(getActivity(), MainActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_user:
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                mPresenter.doLogin(email, password);

                break;
            case R.id.register_link:
                mActivityPresenter.showRegisterView();
                break;
            default:
                break;
        }
    }
}
