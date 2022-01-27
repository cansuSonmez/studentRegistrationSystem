package com.example.studentRegistrationSystem.controller;

import com.example.studentRegistrationSystem.dto.StudentCourseDto;
import com.example.studentRegistrationSystem.model.Student;
import com.example.studentRegistrationSystem.model.StudentCourse;
import com.example.studentRegistrationSystem.service.StudentCourseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/studentCourses",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @PostMapping
    private @ResponseBody
    StudentCourse createStudentCourse (@RequestBody StudentCourseDto studentCourseDto){
        return studentCourseService.createStudentCourse(studentCourseDto);
    }

    @GetMapping("/student/{studentId}")
    private @ResponseBody
    List <StudentCourse> findByStudentIdEquals (@PathVariable Long studentId ){ return  studentCourseService.findByStudentIdEquals(studentId); }

    @GetMapping("/course/{courseId}")
    private @ResponseBody
    List <StudentCourse> findByCourseIdEquals (@PathVariable Long courseId ){ return  studentCourseService.findByCourseIdEquals(courseId); }

    @GetMapping
    private @ResponseBody
    List<StudentCourse> getStudentCourse (){ return (List<StudentCourse>) studentCourseService.getAllStudentCourse(); }

    @PutMapping("/{registerId}")
    private @ResponseBody
    StudentCourse updateStudentCourse(@PathVariable Long registerId, @RequestBody StudentCourseDto studentCourseDto){ return studentCourseService.updateStudentCourse(registerId,studentCourseDto); }

    @PostMapping("/{registerId}")
    private @ResponseBody
    StudentCourse deleteStudent(@PathVariable Long registerId) {
        return studentCourseService.deleteStudentCourse(registerId);
    }

}
