package com.example.studentRegistrationSystem.repository;

import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("select c from Course c where c.courseLimit = ?1")
    List<Course> findByCourseLimitEquals(int courseLimit);

}
