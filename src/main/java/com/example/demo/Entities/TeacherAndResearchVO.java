package com.example.demo.Entities;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherAndResearchVO {
    private Integer RID;
    private String rname;
    private String tname;
    private Integer TID;
    private String email;
    private String phone;
    private String department;
    private String sname;
    private String remark;
    private Date time;
}
