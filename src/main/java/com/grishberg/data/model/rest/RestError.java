package com.grishberg.data.model.rest;

import lombok.Data;

/**
 * Created by grishberg on 20.02.17.
 */
@Data
public class RestError {
    private final int code;
    private final String message;
}
