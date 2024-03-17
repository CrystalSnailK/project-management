package com.example.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialStruct;

@Getter
@Setter
public class Test {
    private Integer code;
    private String msg;
    private Object data;

    public Test(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Test ok(Object data){
        return new Test(0, "成功", data);
    }
    public static Test failed(Object data){
        return new Test(1,"失败", data);
    }


}
