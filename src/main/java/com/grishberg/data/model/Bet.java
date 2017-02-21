package com.grishberg.data.model;

import lombok.Data;
import lombok.Getter;

/**
 * Created by grishberg on 20.02.17.
 */
@Getter
public class Bet {
    private String userName;
    private int bet;

    public Bet(String userName, int bet) {
        this.userName = userName;
        this.bet = bet;
    }
}
