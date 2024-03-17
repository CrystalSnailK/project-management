package com.example.demo.Entities;

import lombok.Data;

import java.util.List;

@Data
public class TableVO {
    private Integer code;
    private String msg;
    private Integer count;
    private Object[] data;

    public static TableVO ok(Object[] data){
        TableVO tableVO = new TableVO();
        tableVO.code = 0;
        tableVO.msg = "ok";
        tableVO.count = data.length;
        tableVO.data = data;
        return tableVO;
    }
    public static TableVO failed(Object[] data){
        TableVO tableVO = new TableVO();
        tableVO.code = 1;
        tableVO.msg = "failed";
        tableVO.count = data.length;
        tableVO.data = data;
        return tableVO;
    }
}
