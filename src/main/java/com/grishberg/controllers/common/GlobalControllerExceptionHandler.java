package com.grishberg.controllers.common;

import com.grishberg.common.RUtils;
import com.grishberg.exception.AppException;
import com.grishberg.exception.ExceptionCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Slf4j
@SuppressWarnings("unused")
@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map handleConflict(Exception e) {
        log.error("exception:", e);
        return RUtils.error(ExceptionCodes.EMPTY_REQUIRED_ARGUMENTS_EXCEPTION, e);
    }

    @ResponseBody
    @ExceptionHandler(AppException.class)
    public Map handleAppException(AppException e) {
        return RUtils.error(e.getCode(), e);
    }
}
