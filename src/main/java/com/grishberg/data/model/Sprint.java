package com.grishberg.data.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by grishberg on 20.02.17.
 */
@Data
public class Sprint {
    private String name;
    private User user;
    private Date creationDate;
}
