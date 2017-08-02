package com.argandevteam.tripreminder.loginregister.register;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.argandevteam.tripreminder.ActivityNavigator;
import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.MainActivity;

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

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
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
    public void registerSuccess() {
        ActivityNavigator activityNavigator = new ActivityNavigator();
        activityNavigator.navigateTo(getActivity(), MainActivity.class, true);
    }

    @Override
    public void registerError() {
        Toast.makeText(getContext(), "Error while registering", Toast.LENGTH_SHORT).show();
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
}
