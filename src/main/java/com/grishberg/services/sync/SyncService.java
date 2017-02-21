package com.grishberg.services.sync;

import com.grishberg.data.model.Bet;
import com.grishberg.data.model.SprintTask;
import com.grishberg.data.model.User;

/**
 * Created by grishberg on 20.02.17.
 */
public interface SyncService {
    SprintTask addNewTaskToSprint(String name, String description);

    void putBet(User user, int betValue);

    Bet[] getBets();
}
