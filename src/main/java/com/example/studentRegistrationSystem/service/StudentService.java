package com.example.studentRegistrationSystem.service;

import com.example.studentRegistrationSystem.dto.StudentDto;
import com.example.studentRegistrationSystem.model.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(StudentDto studentDto);

    Student updateStudent( Long id, StudentDto studentDto);

    Student deleteStudent(Long id);

    List<Student> getAllStudent();

    List<Student> findByCourseLimitEquals(int courseLimit);

}
