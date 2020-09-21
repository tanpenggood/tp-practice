package com.itplh;

public class Result {

    private final String code;
    private final boolean success;
    private final String message;

    public Result(String code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
