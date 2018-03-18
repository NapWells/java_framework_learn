package com.yyh.dto;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private int id;
    private String code;
    private String name;
    private String age;
    private String phoneNo;
    private List<Course> courseList;
}
