package com.grishberg.data.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by grishberg on 20.02.17.
 */
@Data
public class SprintTask implements Serializable{
    private long id;
    private String name;
    private String description;
    private String sprintToken;
}
