package com.grishberg.services.sync;

import com.grishberg.data.model.Bet;
import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.SprintTask;
import com.grishberg.data.model.UserEntity;
import lombok.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by grishberg on 20.02.17.
 */
public class SyncServiceMemoryImpl implements SprintService {
    private final Map<Sprint, SprintTask> tasks;
    private final Map<Sprint, Map<UserEntity, Bet>> bets;
    private final AtomicLong taskId = new AtomicLong();
    private final Map<Sprint, List<UserEntity>> sprintUsers = new ConcurrentHashMap<>();
    private final Map<String, Sprint> sprints = new ConcurrentHashMap<>();

    public SyncServiceMemoryImpl() {
        tasks = new ConcurrentHashMap<>();
        bets = new ConcurrentHashMap<>();
    }

    @Override
    public String startSprint(@NonNull String name) {
        Sprint newSprint = new Sprint();
        newSprint.setName(name);
        newSprint.setCreationDate(new Date());
        Random rand = new Random();
        int tokenValue = rand.nextInt(89999) + 10000;
        String token = String.valueOf(tokenValue);
        sprints.put(token, newSprint);
        sprintUsers.put(newSprint, new ArrayList<>());
        return token;
    }

    @Override
    public SprintTask addNewTaskToSprint(@NonNull String name, @NonNull String description) {
        bets.clear();
        SprintTask task = new SprintTask();
        task.setId(taskId.incrementAndGet());
        task.setDescription(description);
        task.setName(name);
        return task;
    }

    @Override
    public boolean checkSprintToken(@NonNull String token) {
        return sprints.containsKey(token);
    }

    @Override
    public void putBet(@NonNull UserEntity user, int betValue) {
        Sprint sprint = getSprintByUser(user);
        Map<UserEntity, Bet> betsForSprint = bets.get(sprint);
        if (betsForSprint == null) {
            betsForSprint = new ConcurrentHashMap<>();
            bets.put(sprint, betsForSprint);
        }
        betsForSprint.put(user, new Bet(user.getName(), betValue));
    }

    private Sprint getSprintByUser(UserEntity user) {
        return user.getSprint();
    }

    @Override
    public Bet[] getBets(@NonNull Sprint sprint) {
        Map<UserEntity, Bet> betsForSprint = bets.get(sprint);
        if (betsForSprint == null || betsForSprint.size() < getUsersForSprint(sprint).length) {
            return new Bet[0];
        }
        ArrayList<Bet> betsArray = new ArrayList<>();
        for (Map.Entry<UserEntity, Bet> entry : betsForSprint.entrySet()) {
            betsArray.add(entry.getValue());
        }
        return betsArray.toArray(new Bet[betsArray.size()]);
    }

    @Override
    public Sprint getSprintByToken(@NonNull String sprintToken) {
        return sprints.get(sprintToken);
    }

    @Override
    public void addMemberToSprint(UserEntity user, Sprint sprint) {
        List<UserEntity> currentSprintUsers = sprintUsers.get(sprint);
        if (currentSprintUsers == null) {
            currentSprintUsers = new ArrayList<>();
        }
        currentSprintUsers.add(user);
    }

    @Override
    public UserEntity[] getUsersForSprint(Sprint sprint) {
        List<UserEntity> users = sprintUsers.get(sprint);
        return users != null ? users.toArray(new UserEntity[users.size()]) : new UserEntity[0];
    }

    @Override
    public SprintTask getCurrentTask(Sprint sprint) {
        return tasks.get(sprint);
    }
}
