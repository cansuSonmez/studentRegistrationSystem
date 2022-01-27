package com.example.studentRegistrationSystem.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CourseDto {

    @NotEmpty(message = "this field is not null !")
    @Size(min = 2, max = 64)
    public String courseName;

    public CourseDto(String java) {
    }
}
