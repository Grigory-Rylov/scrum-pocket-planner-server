package com.grishberg.services.sync;

import com.grishberg.data.model.Bet;
import com.grishberg.data.model.Sprint;
import com.grishberg.data.model.SprintTask;
import com.grishberg.data.model.UserEntity;

/**
 * Created by grishberg on 20.02.17.
 */
public interface SprintService {
    UserEntity[] getUsersForSprint(Sprint sprint);

    String startSprint(String name);

    SprintTask addNewTaskToSprint(Sprint sprint, String name, String description);

    Sprint getSprintByToken(String sprintToken);

    void addMemberToSprint(UserEntity user, Sprint sprint);

    void putBet(UserEntity user, int betValue);

    Bet[] getBets(Sprint sprint);

    SprintTask getCurrentTask(Sprint sprint);

}
