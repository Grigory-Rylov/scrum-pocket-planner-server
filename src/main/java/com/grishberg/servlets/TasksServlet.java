package com.grishberg.servlets;

import com.grishberg.common.Const;
import com.grishberg.common.JsonSerializer;
import com.grishberg.data.exceptions.Errors;
import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.SprintTask;
import com.grishberg.data.model.User;
import com.grishberg.data.model.rest.RestResponse;
import com.grishberg.services.accounts.AccountService;
import com.grishberg.services.sync.SyncService;
import com.grishberg.servlets.common.BaseHttpServlet;
import lombok.NonNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by grishberg on 20.02.17.
 */
public class TasksServlet extends BaseHttpServlet {
    public static final String PAGE_URL = Const.VER + "/newTask";
    private final SyncService syncService;

    public TasksServlet(AccountService accountService, @NonNull SyncService syncService) {
        super(accountService);
        this.syncService = syncService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Sprint sprint = getSprint(req);
        if (sprint == null) {
            sendAuthError(resp);
            return;
        }

        String taskName = req.getParameter("name");
        String taskDescription = req.getParameter("description");
        resp.setContentType(CONTENT_TYPE);

        if (taskName == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(JsonSerializer.toJson(new RestResponse().setError(Errors.EMPTY_FIELDS_ERROR)));
            return;
        }

        SprintTask newTask = syncService.addNewTaskToSprint(taskName, taskDescription);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(JsonSerializer.toJson(
                new RestResponse<SprintTask>().setData(newTask)));
    }
}
