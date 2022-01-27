package com.example.studentRegistrationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class StudentDto {

    @NotEmpty(message = "this field is not null !")
    @Size(min = 2,max = 50)
    public String studentName;

    @NotNull(message = "this field is not null !")
    public int studentNumber;

}
