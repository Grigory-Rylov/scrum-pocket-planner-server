package com.grishberg.servlets;

import com.grishberg.common.Const;
import com.grishberg.common.JsonSerializer;
import com.grishberg.data.exceptions.Errors;
import com.grishberg.data.model.SprintToken;
import com.grishberg.data.model.rest.RestResponse;
import com.grishberg.services.accounts.AccountService;
import com.grishberg.servlets.common.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by grishberg on 20.02.17.
 */
public class MeetingServlet extends BaseHttpServlet {
    public static final String PAGE_URL = Const.VER + "/sprint";
    private final AccountService accountService;

    public MeetingServlet(AccountService accountService) {
        super(accountService);
        this.accountService = accountService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String meetingName = req.getParameter("name");
        resp.setContentType(CONTENT_TYPE);

        if (meetingName == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(JsonSerializer.toJson(new RestResponse().setError(Errors.AUTH_EMPTY_FIELDS_ERROR)));
            return;
        }
        final String token = accountService.startMeeting(meetingName);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(JsonSerializer.toJson(
                new RestResponse<SprintToken>().setData(new SprintToken(token))));
    }
}
