package com.example.demo.Entities;

import lombok.Data;

import java.util.Date;

@Data
public class updateResearchVO {
    private String rname;
    private String fileLocation;
    private Integer TID;
    private String sname;
    private Date time;
    private String remark;
}
