package com.example.studentRegistrationSystem.controller;

import com.example.studentRegistrationSystem.dto.CourseDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.repository.CourseRepository;
import com.example.studentRegistrationSystem.service.CourseService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers =CourseController.class )
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    public void createCourse() throws Exception{
        CourseDto courseDto = new CourseDto("Java");
        Course course = Course.builder()
                .id(null)
                .courseName("java")
                .courseLimit(0)
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(course));

        Mockito.when(courseRepository.save(course)).thenReturn(course);

        mockMvc.perform(mockRequest)
                .andExpect(status().is5xxServerError());


    }

    @Test
    public void getCourse() throws Exception{
        Course course1 = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(50)
                .build();
        courseRepository.save(course1);

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCourseAll() throws Exception{
        Course course1 = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(50)
                .build();
        courseRepository.save(course1);

        Mockito.when(courseService.getAllCourse()).thenReturn(Collections.singletonList(course1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCourseDifferent() throws Exception{
        Course course1 = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(50)
                .build();
        courseRepository.save(course1);

        Mockito.when(courseService.findByCourseLimitEquals(50)).thenReturn(Collections.singletonList(course1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/courses/getDifferentCourse")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCourse() throws Exception{
        Course course1 = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(50)
                .build();

        courseRepository.save(course1);

        CourseDto courseDto = new CourseDto("jpa");

        Mockito.when(courseService.updateCourse(1L,courseDto)).thenReturn(course1);

        course1.setCourseName(courseDto.getCourseName());
        courseRepository.save(course1);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is5xxServerError());
    }

    @Test
    public void deleteCourse() throws Exception{
        Course course1 = Course.builder()
                .id(1L)
                .courseName("java")
                .courseLimit(50)
                .build();
        courseRepository.save(course1);

        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course1));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}