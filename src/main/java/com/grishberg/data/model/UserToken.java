package com.grishberg.data.model;

/**
 * Created by grishberg on 20.02.17.
 */

import lombok.Getter;

import java.util.Calendar;
import java.util.UUID;
@Getter
public class UserToken {
    private long time;
    private UUID token;

    public UserToken(final UUID token, final long time) {
        this.token = token;
        this.time = time;
    }

    public UserToken(final long tokenDuration) {
        time = Calendar.getInstance().getTimeInMillis() + tokenDuration;
        token = UUID.randomUUID();
    }

    public boolean checkToken(final String token) {
        return this.token.toString().equals(token);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UserToken)) {
            return false;
        }
        UserToken userToken = (UserToken) obj;
        return this.token.equals(userToken.token) && time > userToken.time;
    }
}
