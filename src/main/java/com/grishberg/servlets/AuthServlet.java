package com.grishberg.servlets;

import com.grishberg.common.Const;
import com.grishberg.common.JsonSerializer;
import com.grishberg.data.exceptions.Errors;
import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.SprintWithToken;
import com.grishberg.data.model.User;
import com.grishberg.data.model.rest.RestResponse;
import com.grishberg.services.accounts.AccountService;
import com.grishberg.servlets.common.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.grishberg.servlets.common.BaseHttpServlet.CONTENT_TYPE;

/**
 * Created by grishberg on 20.02.17.
 */
public class AuthServlet extends HttpServlet {
    public static final String PAGE_URL = Const.VER + "/auth";
    private final AccountService accountService;

    public AuthServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String meetingToken = req.getParameter(BaseHttpServlet.SPRINT_TOKEN);

        resp.setContentType(CONTENT_TYPE);

        if (name == null || meetingToken == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(JsonSerializer.toJson(
                    new RestResponse().setError(Errors.AUTH_EMPTY_FIELDS_ERROR)));
            return;
        }

        String accessToken = accountService.registerUser(meetingToken, name);
        Sprint sprintByToken = accountService.getSprintByToken(meetingToken);
        sprintByToken.setUser(new User(name));
        SprintWithToken sprint = new SprintWithToken(sprintByToken, accessToken);

        if (accessToken != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(JsonSerializer.toJson(
                    new RestResponse<SprintWithToken>().setData(sprint)));
            return;
        }

        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.getWriter().println(JsonSerializer.toJson(
                new RestResponse().setError(Errors.AUTH_ERROR)));
    }
}
