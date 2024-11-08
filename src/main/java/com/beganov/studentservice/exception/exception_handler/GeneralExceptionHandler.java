package com.beganov.studentservice.exception.exception_handler;

import com.beganov.studentservice.exception.EmptyValueException;
import com.beganov.studentservice.exception.exception_response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(EmptyValueException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse emptyValueHandle(EmptyValueException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e.getClass().getName(), e.getMessage());
    }
}
