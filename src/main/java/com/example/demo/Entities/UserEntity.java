package com.example.demo.Entities;

import lombok.Data;

@Data
public class UserEntity {
    private int ID;
    private String password;
    private String role;

    public UserEntity(int ID, String password, String role) {
        this.ID = ID;
        this.password = password;
        this.role = role;
    }
    public int getID() {
        return ID;
    }
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
