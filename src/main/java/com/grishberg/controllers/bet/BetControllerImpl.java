package com.grishberg.controllers.bet;

import com.grishberg.common.RUtils;
import com.grishberg.controllers.common.BaseController;
import com.grishberg.data.model.UserEntity;
import com.grishberg.exception.AppException;
import com.grishberg.services.sync.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by grishberg on 24.02.17.
 */
@RestController
@SuppressWarnings("unused")
public class BetControllerImpl extends BaseController implements BetController {
    @Autowired
    private SprintService syncService;

    @Override
    public Map getBets(HttpServletRequest request, @RequestParam String sprintToken) throws AppException.WrongMeetingTokenException {
        return RUtils.success(syncService.getBets(getSprintByToken(sprintToken)));
    }

    @Override
    public Map makeBet(HttpServletRequest request,
                       @RequestParam String accessToken,
                       @RequestParam int bet) throws AppException.WrongAccessTokenException {
        UserEntity user = getUserByAccessToken(accessToken);
        syncService.putBet(user, bet);
        return RUtils.success(true);
    }
}
