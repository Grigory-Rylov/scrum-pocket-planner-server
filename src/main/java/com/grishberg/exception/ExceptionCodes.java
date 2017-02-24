package com.grishberg.exception;

import java.util.NoSuchElementException;

/**
 * Created by grishberg on 24.02.17.
 */
public final class ExceptionCodes {
    public static final int WRONG_MEETING_TOKEN_EXCEPTION = 1000;
    public static final int WRONG_ACCESS_TOKEN_EXCEPTION = 1001;
    public static final int EMPTY_REQUIRED_ARGUMENTS_EXCEPTION = 1002;

    private ExceptionCodes() {
        throw new NoSuchElementException();
    }
}
