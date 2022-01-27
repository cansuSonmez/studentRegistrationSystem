package com.example.studentRegistrationSystem.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorObject {

    private int status;

    private String message;

    private Date timestamp;
}
