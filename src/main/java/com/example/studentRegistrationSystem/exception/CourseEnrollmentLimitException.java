package com.example.studentRegistrationSystem.exception;

public class CourseEnrollmentLimitException extends RuntimeException{

    public CourseEnrollmentLimitException(String param){
        super(param);
    }
}
