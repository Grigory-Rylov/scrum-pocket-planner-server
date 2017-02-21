package com.grishberg.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grishberg.data.model.rest.RestResponse;

/**
 * Created by grishberg on 20.02.17.
 */
public class JsonSerializer {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String toJson(RestResponse<T> response) {
        String jsonInString = null;
        try {
            jsonInString = MAPPER.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }
}
