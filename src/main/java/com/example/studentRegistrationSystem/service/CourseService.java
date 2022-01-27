package com.example.studentRegistrationSystem.service;

import com.example.studentRegistrationSystem.dto.CourseDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;

import java.util.List;

public interface CourseService {

    Course createCourse(CourseDto courseDto);

    Course getCourse(Long id);

    Course updateCourse( Long id, CourseDto courseDto);

    Course deleteCourse(Long id);

    List<Course> getAllCourse();

    List<Course> courseWithoutAStudent();

    List<Course> findByCourseLimitEquals(int courseLimit);
}
