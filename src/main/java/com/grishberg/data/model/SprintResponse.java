package com.grishberg.data.model;

import lombok.Getter;

/**
 * Created by grishberg on 24.02.17.
 */
@Getter
public class SprintResponse {
    private final String name;
    private final String sprintToken;

    public SprintResponse(String name, String sprintToken) {
        this.name = name;
        this.sprintToken = sprintToken;
    }
}
