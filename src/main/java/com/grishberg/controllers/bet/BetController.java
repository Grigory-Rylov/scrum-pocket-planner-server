package com.grishberg.controllers.bet;

import com.grishberg.common.Const;
import com.grishberg.exception.AppException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by grishberg on 24.02.17.
 */
@RestController
@RequestMapping(value = Const.VER)
public interface BetController {
    @RequestMapping(value = "/getBets")
    Map getBets(HttpServletRequest request,
                @RequestParam String sprintToken) throws AppException.WrongMeetingTokenException;

    @RequestMapping(value = "/makeBet", method = POST)
    Map makeBet(HttpServletRequest request,
                @RequestParam String accessToken,
                @RequestParam int bet) throws AppException.WrongAccessTokenException;
}
