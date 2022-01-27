package com.example.studentRegistrationSystem.service.impl;

import com.example.studentRegistrationSystem.dto.StudentCourseDto;
import com.example.studentRegistrationSystem.exception.CourseEnrollmentLimitException;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;
import com.example.studentRegistrationSystem.model.StudentCourse;
import com.example.studentRegistrationSystem.repository.CourseRepository;
import com.example.studentRegistrationSystem.repository.StudentCourseRepository;
import com.example.studentRegistrationSystem.repository.StudentRepository;
import com.example.studentRegistrationSystem.service.StudentCourseService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public StudentCourse createStudentCourse(StudentCourseDto studentCourseDto) {
        long studentNumber = this.studentCourseRepository.countByStudentId(studentCourseDto.getStudentId());
        long courseNumber = this.studentCourseRepository.countByCourseId(studentCourseDto.getCourseId());

        System.out.println("studet number= " + studentNumber+ " course number = "+ courseNumber);

        StudentCourse studentCourse = StudentCourse.builder()
                .studentId(studentCourseDto.getStudentId())
                .courseId(studentCourseDto.getCourseId())
                .build();

        Course existingCourse = this.courseRepository.findById(studentCourseDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException(" This course is not found! "));

        Student existingStudent = this.studentRepository.findById(studentCourseDto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(" This student is not found"));


        if (studentNumber <= 5) {
            if (courseNumber <= 50) {
                this.studentCourseRepository.save(studentCourse);
            } else {
                throw new CourseEnrollmentLimitException(" Course is full! ");

            }
        } else {
            throw new CourseEnrollmentLimitException(" Your course registration has expired ");

        }

        existingStudent.setCourseLimit(existingStudent.getCourseLimit() - 1);
        this.studentRepository.saveAndFlush(existingStudent);

        existingCourse.setCourseLimit(existingCourse.getCourseLimit() -1 );
        this.courseRepository.saveAndFlush(existingCourse);

        return studentCourse;
    }

    @Override
    @Transactional
    public StudentCourse updateStudentCourse(Long id, StudentCourseDto studentCourseDto) {
        StudentCourse existingStudentCourse = this.studentCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudentCourse with this id not found."));

        existingStudentCourse.setStudentId(studentCourseDto.getStudentId());
        existingStudentCourse.setCourseId(studentCourseDto.getCourseId());

        this.studentCourseRepository.saveAndFlush(existingStudentCourse);

        return existingStudentCourse;
    }

    @Override
    @Transactional
    public StudentCourse deleteStudentCourse(Long id) {
        StudentCourse existingStudentCourse = this.studentCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The StudentCourse you want to delete is not registered."));

        this.studentCourseRepository.delete(existingStudentCourse);

        return existingStudentCourse;
    }

    @Override
    @Transactional
    public List<StudentCourse> getAllStudentCourse() {
        return this.studentCourseRepository.findAll();
    }

    @Override
    public List<StudentCourse> findByStudentIdEquals(Long studentId) {
        return this.studentCourseRepository.findByStudentIdEquals(studentId);
    }

    @Override
    public List<StudentCourse> findByCourseIdEquals(Long courseId) {
        return this.studentCourseRepository.findByCourseIdEquals(courseId);
    }


}
