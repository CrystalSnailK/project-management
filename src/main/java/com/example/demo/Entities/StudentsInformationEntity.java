package com.example.demo.Entities;


import lombok.Data;

// 返回学生账号信息（管理员端）
@Data
public class StudentsInformationEntity {
    private int id;
    private String stu_name;
    private String password;
    private String gender; // 性别
    private String department;
    private int grade;
    private String major;
    private int class_num;
    private String email;
    private String phone;
}
