package com.example.studentRegistrationSystem.service.impl;

import com.example.studentRegistrationSystem.dto.StudentDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;
import com.example.studentRegistrationSystem.repository.StudentRepository;
import com.example.studentRegistrationSystem.service.StudentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student createStudent(StudentDto studentDto) {
        Student newStudent = Student.builder()
                .studentName(studentDto.getStudentName())
                .studentNumber(studentDto.getStudentNumber())
                .courseLimit(5)
                .build();
        this.studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with this id not found."));

        existingStudent.setStudentName(studentDto.getStudentName());
        existingStudent.setStudentNumber(studentDto.getStudentNumber());

        return this.studentRepository.saveAndFlush(existingStudent);
    }

    @Override
    @Transactional
    public Student deleteStudent(Long id) {
        Student existingStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The student you want to delete is not registered."));

        this.studentRepository.delete(existingStudent);

        return existingStudent;
    }

    @Override
    @Transactional
    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> findByCourseLimitEquals(int courseLimit) {
        return this.studentRepository.findByCourseLimitEquals(5);
    }

}
