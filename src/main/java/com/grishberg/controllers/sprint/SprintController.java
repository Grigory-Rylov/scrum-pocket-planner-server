package com.grishberg.controllers.sprint;

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
public interface SprintController {
    @RequestMapping(value = "/makeSprint", method = POST)
    Map makeSprint(HttpServletRequest request,
                   @RequestParam String name);

    @RequestMapping(value = "/makeSprintTask", method = POST)
    Map makeSprintTask(HttpServletRequest request,
                       @RequestParam String sprintToken,
                       @RequestParam String name,
                       @RequestParam String description) throws AppException.WrongMeetingTokenException;

    @RequestMapping(value = "/getCurrentTaskForSprint")
    Map getCurrentTaskForSprint(HttpServletRequest request,
                                @RequestParam String sprintToken) throws AppException.WrongMeetingTokenException;

    @RequestMapping(value = "/getSprintInfo")
    Map getSprintInfo(HttpServletRequest request,
                                @RequestParam String sprintToken) throws AppException.WrongMeetingTokenException;
}
