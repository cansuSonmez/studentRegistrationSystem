package com.example.studentRegistrationSystem.repository;

import com.example.studentRegistrationSystem.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long> {

    long countByStudentId(Long studentId);

    long countByCourseId(Long courseId);

    @Query("select s from StudentCourse s where s.courseId = ?1")
    List<StudentCourse> findByCourseIdEquals(Long courseId);

    @Query("select s from StudentCourse s where s.studentId = ?1")
    List<StudentCourse> findByStudentIdEquals(Long studentId);




}
