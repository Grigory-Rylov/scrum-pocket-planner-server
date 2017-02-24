package com.grishberg.data.model;

import lombok.Getter;

/**
 * Created by grishberg on 20.02.17.
 */
@Getter
public class UserEntity {
    private final long id;
    private final String name;
    private final String accessToken;
    private final Sprint sprint;

    public UserEntity(long id, String name, String accessToken, Sprint sprint) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
        this.sprint = sprint;
    }
}