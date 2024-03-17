package com.example.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Select;

@Data
@Getter
@Setter
public class TeacherInformationEntity {
    private int TID;
    private String tname;
    private String department;
    private String phone;
    private String email;


}
