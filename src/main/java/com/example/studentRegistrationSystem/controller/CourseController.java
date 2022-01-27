package com.example.studentRegistrationSystem.controller;

import com.example.studentRegistrationSystem.dto.CourseDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.service.CourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/courses",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    private @ResponseBody
    Course createCourse (@RequestBody CourseDto courseDto){ return courseService.createCourse(courseDto); }

    @GetMapping("/{courseId}")
    private @ResponseBody
    Course getCourse(@PathVariable Long courseId) { return courseService.getCourse(courseId); }

    @GetMapping("/getAll")
    private @ResponseBody
    List<Course> getDifferentCourses() { return courseService.getAllCourse(); }

    @GetMapping("/getDifferentCourse")
    private @ResponseBody
    List<Course> getDifferentCourse() { return courseService.findByCourseLimitEquals(50); }

    @PutMapping("/{courseId}")
    private @ResponseBody
    Course updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) { return courseService.updateCourse(courseId,courseDto); }

    @PostMapping("/{courseId}")
    private @ResponseBody
    Course deleteCourse(@PathVariable Long courseId) {
        return courseService.deleteCourse(courseId);
    }
}
