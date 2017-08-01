package com.argandevteam.tripreminder.createtalk;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.argandevteam.tripreminder.R;
import com.argandevteam.tripreminder.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTalkFragment extends Fragment implements CreateTalkContract.View, View.OnClickListener {

    @BindView(R.id.talk_title)
    EditText talkTitle;

    @BindView(R.id.talk_description)
    EditText talkDescription;

    @BindView(R.id.create_talk)
    Button createTalk;

    private CreateTalkContract.Presenter mPresenter;

    public CreateTalkFragment() {
        // Required empty public constructor
    }

    public static CreateTalkFragment newInstance() {
        return new CreateTalkFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_talk, container, false);

        ButterKnife.bind(this, view);

        createTalk.setOnClickListener(this);

        return view;
    }

    @Override
    public void setPresenter(CreateTalkContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void navigateToTalk(String talkId) {
        MainActivity activity = (MainActivity) getActivity();
        activity.showTalkView(talkId);
    }

    @Override
    public void showTitleIsRequired() {
        talkTitle.setError("Titulo es obligatorio");
    }

    @Override
    public void showCreateTalkError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserNotLogged() {
        //Presentar error al usuario y dejarle que se logee / registre
        Toast.makeText(getContext(), "USER NOT LOGGED", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_talk:
                mPresenter.createTalk(talkTitle.getText().toString(), talkDescription.getText().toString());
                break;
            default:
                break;
        }
    }
}
