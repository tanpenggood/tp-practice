package com.itplh.common;

import lombok.Data;

/**
 * @author: tanpeng
 * @since: 2020-06-10 14:09
 */
@Data
public class Result<T> {

    private int code;
    private String message;
    private boolean success;
    private T data;
    private long timestamp = System.currentTimeMillis();

    private Result(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    private Result(int code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public static Result ok() {
        return new Result(200, "操作成功", true);
    }

    public static <T> Result<T> ok(T data) {
        return new Result(200, "操作成功", true, data);
    }

    public static <T> Result ok(String message, T data) {
        return new Result(200, message, true, data);
    }

    public static Result error() {
        return new Result(200, "操作失败", false);
    }

    public static <T> Result error(T data) {
        return new Result(200, "操作失败", false, data);
    }

    public static <T> Result error(String message, T data) {
        return new Result(200, message, false, data);
    }

}
