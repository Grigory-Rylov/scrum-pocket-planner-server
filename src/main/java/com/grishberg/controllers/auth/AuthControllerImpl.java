package com.grishberg.controllers.auth;

import com.grishberg.common.RUtils;
import com.grishberg.controllers.common.BaseController;
import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.User;
import com.grishberg.data.model.UserEntity;
import com.grishberg.exception.AppException.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by grishberg on 24.02.17.
 * members registration
 */
@RestController
@SuppressWarnings("unused")
public class AuthControllerImpl extends BaseController implements AuthController {
    @Override
    public Map registerMember(HttpServletRequest request,
                              @RequestParam String name,
                              @RequestParam String sprintToken) throws WrongMeetingTokenException {

        Sprint sprintByToken = getSprintByToken(sprintToken);
        UserEntity newUser = getAccountService().registerUser(sprintByToken, name);
        getSprintService().addMemberToSprint(newUser, sprintByToken);
        return RUtils.success(User.fromUserEntity(newUser));
    }
}
