package com.example.studentRegistrationSystem.service.impl;
import com.example.studentRegistrationSystem.dto.CourseDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;
import com.example.studentRegistrationSystem.repository.CourseRepository;
import com.example.studentRegistrationSystem.service.CourseService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public Course createCourse(CourseDto courseDto) {
        Course courseCreated = Course.builder()
                .courseName(courseDto.getCourseName())
                .courseLimit(50)
                .build();

        this.courseRepository.save(courseCreated);
        return courseCreated;
    }

    @Override
    @Transactional
    public Course getCourse(Long id) {
        return this.courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course with this id not found."));
    }

    @Override
    @Transactional
    public Course updateCourse(Long id, CourseDto courseDto) {
        Course existingCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with this id not found."));

        existingCourse.setCourseName(courseDto.getCourseName());

        return this.courseRepository.save(existingCourse);
    }

    @Override
    @Transactional
    public Course deleteCourse(Long id) {
        Course existingCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The course you want to delete is not registered."));

        this.courseRepository.delete(existingCourse);

        return existingCourse;
    }

    @Override
    public List<Course> getAllCourse() {
        return this.courseRepository.findAll();
    }

    @Override
    public List<Course> courseWithoutAStudent() {
        return null;
    }

    @Override
    public List<Course> findByCourseLimitEquals(int courseLimit) {
        return this.courseRepository.findByCourseLimitEquals(50);
    }


}
