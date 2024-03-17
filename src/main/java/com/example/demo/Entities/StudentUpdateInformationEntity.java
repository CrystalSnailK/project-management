package com.example.demo.Entities;

import lombok.Data;

// 学生端可更改的学生信息
@Data
public class StudentUpdateInformationEntity {
    private String phone;
    private String email;
}
