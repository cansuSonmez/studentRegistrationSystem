package com.example.studentRegistrationSystem.controller;

import com.example.studentRegistrationSystem.dto.StudentDto;
import com.example.studentRegistrationSystem.model.Course;
import com.example.studentRegistrationSystem.model.Student;
import com.example.studentRegistrationSystem.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/students",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class StudentController {

    private  final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    private @ResponseBody
    Student createStudent (@RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/getDifferentStudents")
    private @ResponseBody
    List<Student> getDifferentStudents() { return studentService.findByCourseLimitEquals(5); }

    @GetMapping("/getAll")
    private @ResponseBody
    List<Student> getAll() { return studentService.getAllStudent(); }

    @PutMapping("/{studentId}")
    private @ResponseBody
    Student updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto){ return studentService.updateStudent(studentId,studentDto); }

    @PostMapping("/{studentId}")
    private @ResponseBody
    Student deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }
}
