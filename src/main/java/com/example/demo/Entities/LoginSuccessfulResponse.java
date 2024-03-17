package com.example.demo.Entities;

public class LoginSuccessfulResponse {
    String msg;
    String token;
    String role;
    int ID;
    public LoginSuccessfulResponse(String msg, String token, String role, int ID) {
        this.msg = msg;
        this.token = token;
        this.role = role;
        this.ID = ID;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public int getID() {
        return ID;
    }
}
