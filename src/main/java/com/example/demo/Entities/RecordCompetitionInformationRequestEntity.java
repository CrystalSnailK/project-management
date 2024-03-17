package com.example.demo.Entities;

import lombok.Data;


// 记录比赛信息
@Data
public class RecordCompetitionInformationRequestEntity {
    String c_name; // 比赛名称
    String c_time;// 比赛时间 yyyy-mm-dd
    String grade; // 等级
    String host; // 举办方
    String comment; // 备注
}
