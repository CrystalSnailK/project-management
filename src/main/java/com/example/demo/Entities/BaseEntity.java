package com.example.demo.Entities;

import lombok.Data;

@Data
public class BaseEntity {
    private int code;
    private String msg;
    private int count;
    private Object data;

    public BaseEntity(int code, String msg, int count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
