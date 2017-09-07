package com.argandevteam.tripreminder.data.source;

import com.argandevteam.tripreminder.data.User;

/**
 * Created by markc on 16/08/2017.
 */

public interface UsersDataSource {
    void getUser(String userId);

    void saveUser(User user);

    void editUser(User user);
}
