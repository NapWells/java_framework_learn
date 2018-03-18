package com.yyh.controller;

import com.yyh.dao.CourseDao;
import com.yyh.dto.Course;
import com.yyh.dto.Student;
import com.yyh.dao.StudentDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CourseDao courseDao;

    @RequestMapping("/query/student/{id}")
    public Student queryStudentById(@PathVariable int id){
        return studentDao.findStudentById(id);
    }

    @RequestMapping("/query/student/all")
    public List<Student> queryAllStudent(){
        return studentDao.findAllStudent();
    }

    @RequestMapping(value = "/add/course",method= RequestMethod.POST)
    public int addCurse(@RequestBody Course course){
        return courseDao.insertCourse(course);
    }

    @RequestMapping(value = "/query/course")
    public List<Course> queryCourse(@RequestParam int studentId){
        return courseDao.selectCoursesByStudentId(studentId);
    }

}
