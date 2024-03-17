package com.example.demo.Entities;

import lombok.Data;

// 录入个人奖项
@Data
public class RecordPersonalAwardEntity {
    private String c_name;
    private String c_time;

    private String grade;
    // 指导老师名称
    private String tea_name;
    // 图片位置
    private String locateImage;
}
