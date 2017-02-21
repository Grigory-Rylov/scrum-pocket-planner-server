package com.grishberg.services.accounts;

import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by grishberg on 20.02.17.
 */
public class AccountServiceInMemoryImpl implements AccountService {
    private final Map<String, Sprint> sprints = new ConcurrentHashMap<>();
    private final Map<String, User> sprintUsers = new ConcurrentHashMap<>();

    @Override
    public String startMeeting(String meetingName) {
        Sprint newSprint = new Sprint();
        newSprint.setName(meetingName);
        newSprint.setCreationDate(new Date());
        Random rand = new Random();
        int tokentValue = rand.nextInt(89999) + 10000;
        String token = String.valueOf(tokentValue);
        sprints.put(token, newSprint);
        return token;
    }

    @Override
    public boolean checkMeetingToken(String token) {
        return sprints.containsKey(token);
    }

    @Override
    public String registerUser(String meetingToken, String name) {
        String accessToken = UUID.randomUUID().toString();
        sprintUsers.put(accessToken, new User(name));
        return accessToken;
    }

    @Override
    public User getUserByAccessToken(String accessToken) {
        return sprintUsers.get(accessToken);
    }

    @Override
    public Sprint getSprintByToken(String sprintToken) {
        return sprints.get(sprintToken);
    }

    @Override
    public User[] getUsers(String meetingToken) {
        ArrayList<User> usersArray = new ArrayList<>();
        for (Map.Entry<String, User> entry : sprintUsers.entrySet()) {
            usersArray.add(entry.getValue());
        }
        return usersArray.toArray(new User[usersArray.size()]);
    }
}
