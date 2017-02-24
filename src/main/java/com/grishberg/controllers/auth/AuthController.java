package com.grishberg.controllers.auth;

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
public interface AuthController {

    @RequestMapping(value = "/registerMember", method = POST)
    Map registerMember(HttpServletRequest request,
                       @RequestParam String name,
                       @RequestParam String sprintToken) throws AppException.WrongMeetingTokenException;

}
