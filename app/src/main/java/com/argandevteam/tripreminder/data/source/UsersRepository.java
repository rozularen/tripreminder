package com.argandevteam.tripreminder.data.source;

import com.argandevteam.tripreminder.data.User;

/**
 * Created by markc on 16/08/2017.
 */

public class UsersRepository implements UsersDataSource {
    private UsersDataSource mUsersLocalDataSource;
    private UsersDataSource mUsersRemoteDataSource;
    private UsersRepository mUsersRepository;

    public UsersRepository(UsersDataSource mUsersLocalDataSource, UsersDataSource mUsersRemoteDataSource, UsersRepository mUsersRepository) {
        this.mUsersLocalDataSource = mUsersLocalDataSource;
        this.mUsersRemoteDataSource = mUsersRemoteDataSource;
        this.mUsersRepository = mUsersRepository;
    }

    @Override
    public void getUser(String userId) {
        if (userId != null) {
        }
    }

    @Override
    public void saveUser(User user) {
        if (user != null) {
            mUsersLocalDataSource.saveUser(user);
        }
    }

    @Override
    public void editUser(User user) {

    }
}
