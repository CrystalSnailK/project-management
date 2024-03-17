package com.example.demo.Entities;

import lombok.Data;

@Data
public class StudentInformationEntity {
    private int SID;
    private String stu_name;
    private String gender;
    private String department;
    private int grade;
    private String major;
    private int class_num;
    private String phone;
    private String email;
}
