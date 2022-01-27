package com.example.studentRegistrationSystem.controller;

import com.example.studentRegistrationSystem.dto.CourseDto;
import com.example.studentRegistrationSystem.dto.StudentDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;
import com.example.studentRegistrationSystem.repository.CourseRepository;
import com.example.studentRegistrationSystem.repository.StudentRepository;
import com.example.studentRegistrationSystem.service.CourseService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers =StudentController.class )
class StudentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void createStudent() throws Exception{
        StudentDto studentDto = new StudentDto("cansu",1233);
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(student));

        Mockito.when(studentRepository.save(student)).thenReturn(student);

        mockMvc.perform(mockRequest)
                .andExpect(status().is5xxServerError());

    }

    @Test
    public void getStudentAll() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();
        studentRepository.save(student);

        Mockito.when(studentService.getAllStudent()).thenReturn(Collections.singletonList(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentDifferent() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();
        studentRepository.save(student);

        Mockito.when(studentService.findByCourseLimitEquals(50)).thenReturn(Collections.singletonList(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/getDifferentStudents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudent() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();

        studentRepository.save(student);

        StudentDto studentDto = new StudentDto("cansu",1233);

        Mockito.when(studentService.updateStudent(1L,studentDto)).thenReturn(student);

        student.setStudentName(studentDto.getStudentName());
        studentRepository.save(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void deleteStudent() throws Exception{
        Student student = Student.builder()
                .id(1L)
                .studentName("cansu")
                .studentNumber(123)
                .courseLimit(5)
                .build();
        studentRepository.save(student);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}