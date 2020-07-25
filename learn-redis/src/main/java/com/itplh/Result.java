package com.itplh;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



/**
 * @author: tanpenggood
 * @date: 2020-07-25 19:57
 * @since: java 8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> {

    private int code = 200;
    private boolean success;
    private String msg;
    private T data;
    private long timestamp = System.currentTimeMillis();

    public static Result ok() {
        return Result.builder().success(true).msg("操作成功").build();
    }

    public static<T> Result ok(String msg) {
        return Result.ok().setMsg(msg);
    }

    public static<T> Result ok(T data) {
        return Result.ok().setData(data);
    }

    public static Result error() {
        return Result.builder().success(false).msg("操作失败").build();
    }

    public static<T> Result error(String msg) {
        return Result.error().setMsg(msg);
    }

    public static<T> Result error(T data) {
        return Result.error().setData(data);
    }

}
