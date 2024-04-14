package com.myli.controller;


import lombok.Data;

@Data
public class Result {
    Integer code;
    Object data;
    String msg;

    public Result() {}

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
