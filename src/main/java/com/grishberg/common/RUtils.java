package com.grishberg.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by grishberg on 24.02.17.
 */
public class RUtils {
    private final static String DATA_KEY = "data";
    private final static String ERROR_KEY = "error";

    public static Map<String, Object> success(Object object) {
        Map<String, Object> response = new HashMap<>();

        response.put(DATA_KEY, object);
        response.put(ERROR_KEY, null);

        return response;
    }

    public static Map<String, Object> error(int code, Throwable throwable) {
        Map<String, Object> response = new HashMap<>();

        response.put(DATA_KEY, null);
        response.put(ERROR_KEY, new RestError(code, throwable.getMessage()));

        return response;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RestError {
        private int code;
        private String message;
    }
}
