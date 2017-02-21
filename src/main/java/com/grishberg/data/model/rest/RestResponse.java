package com.grishberg.data.model.rest;

import lombok.Getter;

/**
 * Created by grishberg on 20.02.17.
 */
@Getter
public class RestResponse<T> {
    private T data;
    private RestError error;

    public RestResponse<T> setData(final T data) {
        this.data = data;
        this.error = null;
        return this;
    }

    public RestResponse<T> setError(final int code, final String message) {
        this.error = new RestError(code, message);
        this.data = null;
        return this;
    }

    public RestResponse<T> setError(final RestError error) {
        this.error = error;
        this.data = null;
        return this;
    }
}
