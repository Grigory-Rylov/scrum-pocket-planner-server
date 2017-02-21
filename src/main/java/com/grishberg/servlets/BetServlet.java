package com.grishberg.servlets;

import com.grishberg.common.Const;
import com.grishberg.common.JsonSerializer;
import com.grishberg.data.model.Bet;
import com.grishberg.data.model.User;
import com.grishberg.data.model.rest.RestResponse;
import com.grishberg.services.accounts.AccountService;
import com.grishberg.services.sync.SyncService;
import com.grishberg.servlets.common.BaseHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by grishberg on 20.02.17.
 */
public class BetServlet extends BaseHttpServlet {
    public static final String PAGE_URL = Const.VER + "/bet";
    private final SyncService syncService;

    public BetServlet(AccountService accountService, SyncService syncService) {
        super(accountService);
        this.syncService = syncService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = checkToken(req);
        if (user == null) {
            if (getSprint(req) == null) {
                sendAuthError(resp);
                return;
            }
        }

        resp.setContentType(CONTENT_TYPE);
        Bet[] bets = syncService.getBets();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(JsonSerializer.toJson(
                new RestResponse<Bet[]>().setData(bets)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sprint = checkToken(req);
        if (sprint == null) {
            sendAuthError(resp);
            return;
        }

        int betValue = getIntParam(req, "value");
        resp.setContentType(CONTENT_TYPE);

        syncService.putBet(sprint, betValue);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(JsonSerializer.toJson(
                new RestResponse<Boolean>().setData(true)));
    }
}
