package com.grishberg.services.accounts;

import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.User;

/**
 * Created by grishberg on 20.02.17.
 */
public interface AccountService {
    String startMeeting(String meetingName);

    boolean checkMeetingToken(String token);

    String registerUser(String meetingToken, String userName);

    User getUserByAccessToken(String accessToken);

    Sprint getSprintByToken(String accessToken);

    User[] getUsers(String meetingToken);
}
