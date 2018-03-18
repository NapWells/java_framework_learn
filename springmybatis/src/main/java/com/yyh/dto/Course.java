package com.yyh.dto;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private int id;
    private String name;
    private String teacher;
    private List<Student> studentList;
}
