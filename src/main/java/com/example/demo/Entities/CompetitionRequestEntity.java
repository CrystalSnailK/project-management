package com.example.demo.Entities;

import lombok.Data;

@Data
public class CompetitionRequestEntity {
    private String c_name;
    private String c_time;
    private String level;// 类别
    private String host;
    private String comment;
}
