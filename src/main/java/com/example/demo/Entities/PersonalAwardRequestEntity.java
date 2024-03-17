package com.example.demo.Entities;

import lombok.Data;

@Data
public class PersonalAwardRequestEntity {
    private String c_name;
    private String c_time;
    private String grade; // 获奖等级
    private String host;
    private String level; // 类别
//    private String href;
}
