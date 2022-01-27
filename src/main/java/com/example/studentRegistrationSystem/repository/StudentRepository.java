package com.example.studentRegistrationSystem.repository;

import com.example.studentRegistrationSystem.model.Student;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

  @Query("select s from Student s where s.courseLimit = ?1")
  List<Student> findByCourseLimitEquals(int courseLimit);








}
