package com.grishberg.exception;

import lombok.Getter;

import static com.grishberg.exception.ExceptionCodes.WRONG_ACCESS_TOKEN_EXCEPTION;
import static com.grishberg.exception.ExceptionCodes.WRONG_MEETING_TOKEN_EXCEPTION;

/**
 * Created by grishberg on 24.02.17.
 */
@Getter
public class AppException extends Exception {
    private int code;

    public AppException(String message) {
        super(message);
    }

    private AppException(int code, String message) {
        super(message);
        this.code = code;
    }

    public static final class WrongMeetingTokenException extends AppException {
        public WrongMeetingTokenException() {
            super(WRONG_MEETING_TOKEN_EXCEPTION, "Wrong meeting token");
        }
    }

    public static final class WrongAccessTokenException extends AppException {
        public WrongAccessTokenException() {
            super(WRONG_ACCESS_TOKEN_EXCEPTION, "Wrong access token");
        }
    }
}
