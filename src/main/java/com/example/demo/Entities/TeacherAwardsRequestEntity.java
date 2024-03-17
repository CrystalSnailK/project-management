package com.example.demo.Entities;

import lombok.Data;

// 老师带领的学生（所有，by TID）
@Data
public class TeacherAwardsRequestEntity {
    private int id;
    private String stu_name;
    private String c_name;
    private String time;
    private String grade;
    private String host;
    private String level;
//    private String href;// 图片
}
