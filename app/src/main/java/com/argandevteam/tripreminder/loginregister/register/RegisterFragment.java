package com.argandevteam.tripreminder.loginregister.register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.loginregister.LoginRegisterContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements RegisterContract.View, View.OnClickListener {

    @BindView(R.id.user_email)
    EditText userEmail;

    @BindView(R.id.user_first_name)
    EditText userFirstName;

    @BindView(R.id.user_last_name)
    EditText userLastName;

    @BindView(R.id.user_password)
    EditText userPassword;

    @BindView(R.id.register_user)
    Button registerUser;

    private RegisterContract.Presenter mPresenter;
    private LoginRegisterContract.Presenter mActivityPresenter;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, view);

        registerUser.setOnClickListener(this);

        return view;
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setActivityPresenter(LoginRegisterContract.Presenter activityPresenter) {
        mActivityPresenter = activityPresenter;
    }

    @Override
    public void registerSuccess() {
        //Show message
        mActivityPresenter.showMainActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_user:
                String email = userEmail.getText().toString();
                String firstName = userFirstName.getText().toString();
                String lastName = userLastName.getText().toString();
                String password = userPassword.getText().toString();

                mPresenter.registerUser(email, firstName, lastName, password);
                break;
            default:
                break;
        }
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }
}
