package com.example.studentRegistrationSystem.service;

import com.example.studentRegistrationSystem.dto.StudentCourseDto;
import com.example.studentRegistrationSystem.model.StudentCourse;

import java.util.List;

public interface StudentCourseService {

    StudentCourse createStudentCourse(StudentCourseDto studentCourseDto);

    StudentCourse updateStudentCourse( Long id, StudentCourseDto studentCourseDto);

    StudentCourse deleteStudentCourse(Long id);

    List<StudentCourse> getAllStudentCourse();

    List<StudentCourse> findByStudentIdEquals(Long studentId);

    List<StudentCourse> findByCourseIdEquals(Long courseId);


}
