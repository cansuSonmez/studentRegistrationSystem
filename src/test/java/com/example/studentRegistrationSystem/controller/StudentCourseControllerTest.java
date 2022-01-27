package com.example.studentRegistrationSystem.controller;

import com.example.studentRegistrationSystem.dto.CourseDto;
import com.example.studentRegistrationSystem.dto.StudentCourseDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;
import com.example.studentRegistrationSystem.model.StudentCourse;
import com.example.studentRegistrationSystem.repository.CourseRepository;
import com.example.studentRegistrationSystem.repository.StudentCourseRepository;
import com.example.studentRegistrationSystem.repository.StudentRepository;
import com.example.studentRegistrationSystem.service.CourseService;
import com.example.studentRegistrationSystem.service.StudentCourseService;
import com.example.studentRegistrationSystem.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers =StudentCourseController.class )
class StudentCourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentCourseService service;

    @MockBean
    private StudentCourseRepository repository;

     @MockBean
     private StudentRepository studentRepository;

     @MockBean
     private CourseRepository courseRepository;

    @Test
    public void createStudentCourse() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();
        studentRepository.save(student);

        Course course = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(0)
                .build();
        courseRepository.save(course);

        StudentCourseDto studentCourseDto = new StudentCourseDto();
        studentCourseDto.setStudentId(1L);
        studentCourseDto.setCourseId(1L);

        StudentCourse studentCourse = StudentCourse.builder()
                .id(1L)
                .studentId(1L)
                .courseId(1L)
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/studentCourses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(studentCourseDto));

        Mockito.when(repository.save(studentCourse)).thenReturn(studentCourse);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());

    }

    @Test
    public void getStudentCourse() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();
        studentRepository.save(student);

        Course course = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(0)
                .build();
        courseRepository.save(course);

        StudentCourse studentCourse = StudentCourse.builder()
                .id(1L)
                .studentId(1L)
                .courseId(1L)
                .build();

        repository.save(studentCourse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/studentCourses")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void updateCourse() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();
        studentRepository.save(student);

        Course course = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(0)
                .build();
        courseRepository.save(course);

        StudentCourseDto studentCourseDto = new StudentCourseDto();
        studentCourseDto.setStudentId(1L);
        studentCourseDto.setCourseId(1L);

        StudentCourse studentCourse = StudentCourse.builder()
                .id(1L)
                .studentId(1L)
                .courseId(1L)
                .build();

        repository.save(studentCourse);

        CourseDto courseDto = new CourseDto("jpa");

        studentCourse.setStudentId(studentCourseDto.getStudentId());
        repository.save(studentCourse);

        Mockito.when(service.updateStudentCourse(1L,studentCourseDto)).thenReturn(studentCourse);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/studentCourses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void deleteCourse() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();
        studentRepository.save(student);

        Course course = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(0)
                .build();
        courseRepository.save(course);

        StudentCourse studentCourse = StudentCourse.builder()
                .id(1L)
                .studentId(1L)
                .courseId(1L)
                .build();

        repository.save(studentCourse);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(studentCourse));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/studentCourses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}