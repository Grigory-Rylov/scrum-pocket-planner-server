package com.grishberg.services.sync;

import com.grishberg.data.model.Bet;
import com.grishberg.data.model.SprintTask;
import com.grishberg.data.model.User;
import com.grishberg.services.accounts.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by grishberg on 20.02.17.
 */
public class SyncServiceMemoryImpl implements SyncService {
    private final List<SprintTask> tasks;
    private final Map<User, Bet> bets;
    private final AtomicLong taskId = new AtomicLong();
    private final AccountService accountService;

    public SyncServiceMemoryImpl(AccountService accountService) {
        this.accountService = accountService;
        tasks = new CopyOnWriteArrayList<>();
        bets = new ConcurrentHashMap<>();
    }

    @Override
    public SprintTask addNewTaskToSprint(String name, String description) {
        bets.clear();
        SprintTask task = new SprintTask();
        task.setId(taskId.incrementAndGet());
        task.setDescription(description);
        task.setName(name);
        return task;
    }

    @Override
    public void putBet(User user, int betValue) {
        bets.put(user, new Bet(user.getName(), betValue));
    }

    @Override
    public Bet[] getBets() {
        ArrayList<Bet> betsArray = new ArrayList<>();
        for (Map.Entry<User, Bet> entry : bets.entrySet()) {
            betsArray.add(entry.getValue());
        }
        return betsArray.toArray(new Bet[betsArray.size()]);
    }
}
