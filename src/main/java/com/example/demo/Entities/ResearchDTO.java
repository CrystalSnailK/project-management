package com.example.demo.Entities;

import lombok.Data;

import java.util.Date;

@Data
public class ResearchDTO {
    private  Integer RID;
    // 项目名称
    private String rname;
    // 教师名称
    private String tname;
    // 所属学院
    private String department;
    // 参与学生
    private String sname;
    // 录入时间
    private Date time;

}
