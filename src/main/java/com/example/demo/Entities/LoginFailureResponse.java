package com.example.demo.Entities;

public class LoginFailureResponse {
    private String msg;
    public LoginFailureResponse(String msg) {
        this.msg = msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
