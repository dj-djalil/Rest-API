package com.example.demo;

import com.example.module.UserErrorResponse;
import com.example.module.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {
    // add exception handler using @ExceptionHandler
    // not found
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException userNotFoundException){
        // creation d'une response
        UserErrorResponse error = new UserErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(userNotFoundException.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // return ResponseEntity
        // jackson convert error to JSON automaticly (error <-> response body)
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    // handle generic exception
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> genericException (Exception e){
        UserErrorResponse error = new UserErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
