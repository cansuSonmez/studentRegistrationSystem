package com.example.studentRegistrationSystem.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudentCourseDto {

    @NotNull(message = "this field is not null !")
    public Long studentId;

    @NotNull(message = "this field is not null !")
    public Long courseId;
}
