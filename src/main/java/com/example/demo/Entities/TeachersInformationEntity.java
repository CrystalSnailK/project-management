package com.example.demo.Entities;

import lombok.Data;

// 管理员端，教师账号
@Data
public class TeachersInformationEntity {
    private int id;
    private String t_name;
    private String password;
    private String department;
    private String email;
    private String phone;
}
