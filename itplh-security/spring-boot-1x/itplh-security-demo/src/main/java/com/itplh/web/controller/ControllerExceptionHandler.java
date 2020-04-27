package com.itplh.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-19 17:41
 * @version: v1.0.0
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleBindException(Exception ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", ex.getMessage());
        return result;
    }

}
