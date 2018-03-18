package com.yyh.dao;

;
import com.yyh.dto.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentDao {
    //注意#和$的区别，mybatis 在对sql语句进行预编译之前，会对 sql 进行动态解析。
    // #{}预编译之后是一个占位符？
    // ${}预编译以后直接是替换作用

    @Select({"SELECT * FROM student WHERE id = #{id}"})
    @Results(id ="id",
            value = {@Result(property = "courseList", column = "id", many = @Many(select = "com.yyh.dao.CourseDao.selectCoursesByStudentId")),
                    @Result(property = "id",column = "id")
            }
    )
    Student findStudentById(int id);

    @Select("SELECT * FROM student")
    List<Student> findAllStudent();

}
