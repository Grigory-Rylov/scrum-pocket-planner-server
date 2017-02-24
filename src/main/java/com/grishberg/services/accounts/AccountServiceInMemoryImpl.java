package com.grishberg.services.accounts;

import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.UserEntity;
import lombok.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by grishberg on 20.02.17.
 */
public class AccountServiceInMemoryImpl implements AccountService {
    private final AtomicLong userId = new AtomicLong();
    private final Map<String, UserEntity> userAccessTokens = new ConcurrentHashMap<>();

    @Override
    public UserEntity registerUser(@NonNull Sprint sprint, @NonNull String userName) {
        String accessToken = UUID.randomUUID().toString();
        UserEntity user = new UserEntity(userId.incrementAndGet(), userName, accessToken, sprint);
        userAccessTokens.put(accessToken, user);
        return user;
    }

    @Override
    public UserEntity getUserByAccessToken(@NonNull String accessToken) {
        return userAccessTokens.get(accessToken);
    }
}
