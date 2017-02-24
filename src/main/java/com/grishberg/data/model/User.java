package com.grishberg.data.model;

import lombok.Getter;

/**
 * Created by grishberg on 24.02.17.
 */
@Getter
public class User {
    private String name;
    private String accessToken;

    public static User fromUserEntity(UserEntity userEntity) {
        User user = new User();
        user.name = userEntity.getName();
        user.accessToken = userEntity.getAccessToken();
        return user;
    }
}
