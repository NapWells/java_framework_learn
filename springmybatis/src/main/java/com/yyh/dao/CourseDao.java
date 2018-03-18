package com.yyh.dao;

import com.yyh.dto.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CourseDao {
    @Insert("INSERT INTO course (`name`,`teacher`) VALUES (#{name},#{teacher})")
    int insertCourse(Course course);


    @Select("SELECT * FROM course WHERE id IN (SELECT course_id FROM score WHERE student_id = #{studentId})")
    List<Course> selectCoursesByStudentId(@Param("studentId") int studentId);
}
