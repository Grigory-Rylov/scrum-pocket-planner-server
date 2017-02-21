package com.grishberg.data.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by grishberg on 21.02.17.
 */
@Setter
@Getter
public class SprintWithToken extends Sprint {
    private String accessToken;

    public SprintWithToken(Sprint sprint, String accessToken) {
        this.setName(sprint.getName());
        this.accessToken = accessToken;
        this.setUser(sprint.getUser());
        this.setCreationDate(sprint.getCreationDate());
    }
}
