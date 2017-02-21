package com.grishberg.data.model;

import lombok.Getter;

/**
 * Created by grishberg on 20.02.17.
 */
@Getter
public class User {
    private long id;
    private String name;

    public User(String name) {
        this.name = name;
    }
}