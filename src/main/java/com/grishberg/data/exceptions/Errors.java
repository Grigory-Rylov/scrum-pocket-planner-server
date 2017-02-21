package com.grishberg.data.exceptions;

import com.grishberg.data.model.rest.RestError;

/**
 * Created by grishberg on 20.02.17.
 */
public final class Errors {
    public static final RestError AUTH_ERROR = new RestError(1000, "Bad login or password");
    public static final RestError AUTH_EMPTY_FIELDS_ERROR = new RestError(1001, "Empty login or password");
    public static final RestError EMPTY_FIELDS_ERROR = new RestError(1001, "Empty field");
    public static final RestError NOT_AUTHORIZED = new RestError(1002, "Not authorized");
}