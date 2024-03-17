package com.example.demo.Entities;

import lombok.Data;

import java.util.Date;

@Data
public class ResearchPO {
    private String RID;
    private String rname;
    private String fileLocation;
    private String tname;
    private String sname;
    private Date time;
    private String remark;
}
