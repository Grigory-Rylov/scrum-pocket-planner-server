package com.grishberg.controllers.common;

import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.UserEntity;
import com.grishberg.exception.AppException;
import com.grishberg.services.accounts.AccountService;
import com.grishberg.services.sync.SprintService;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by grishberg on 24.02.17.
 */
@SuppressWarnings("unused")
public class BaseController {
    @Autowired
    @Getter
    private AccountService accountService;
    @Autowired
    @Getter
    private SprintService sprintService;

    protected Sprint getSprintByToken(@NonNull String meetingToken) throws AppException.WrongMeetingTokenException {
        Sprint sprintByToken = sprintService.getSprintByToken(meetingToken);
        if (sprintByToken == null) {
            throw new AppException.WrongMeetingTokenException();
        }
        return sprintByToken;
    }

    protected UserEntity getUserByAccessToken(@NonNull String accessToken) throws AppException.WrongAccessTokenException {
        UserEntity user = accountService.getUserByAccessToken(accessToken);
        if (user == null) {
            throw new AppException.WrongAccessTokenException();
        }
        return user;
    }
}
