package com.grishberg.controllers.sprint;

import com.grishberg.common.RUtils;
import com.grishberg.controllers.common.BaseController;
import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.SprintResponse;
import com.grishberg.exception.AppException;
import com.grishberg.exception.AppException.WrongMeetingTokenException;
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
public class SprintControllerImpl extends BaseController implements SprintController {
    @Autowired
    private SprintService syncService;

    @Override
    public Map makeSprint(HttpServletRequest request, @RequestParam String name) {
        final String sprintToken = syncService.startSprint(name);
        return RUtils.success(new SprintResponse(name, sprintToken));
    }

    @Override
    public Map makeSprintTask(HttpServletRequest request,
                              @RequestParam String sprintToken,
                              @RequestParam String name,
                              @RequestParam String description) throws WrongMeetingTokenException {
        Sprint sprint = getSprintByToken(sprintToken);
        return RUtils.success(syncService.addNewTaskToSprint(sprint, name, description));
    }

    @Override
    public Map getCurrentTaskForSprint(HttpServletRequest request, @RequestParam String sprintToken) throws WrongMeetingTokenException {
        Sprint sprint = getSprintByToken(sprintToken);
        return RUtils.success(syncService.getCurrentTask(sprint));
    }

    @Override
    public Map getSprintInfo(HttpServletRequest request, @RequestParam String sprintToken) throws WrongMeetingTokenException {
        Sprint sprint = getSprintByToken(sprintToken);
        return RUtils.success(sprint);
    }
}
