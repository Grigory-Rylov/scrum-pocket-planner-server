package com.grishberg.services.accounts;

import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.UserEntity;

/**
 * Created by grishberg on 20.02.17.
 */
public interface AccountService {

    UserEntity registerUser(Sprint sprint, String userName);

    UserEntity getUserByAccessToken(String accessToken);

}
