package com.grishberg.data.model;

import lombok.Getter;

import java.io.Serializable;

/**
 * Created by grishberg on 21.02.17.
 */
@Getter
public class SprintToken implements Serializable{
    private final String sprintToken;

    public SprintToken(String sprintToken) {
        this.sprintToken = sprintToken;
    }
}
