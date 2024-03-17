package com.example.demo.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTest {
    private String src;
    public DataTest(String src){
        this.src = src;
    }
}
