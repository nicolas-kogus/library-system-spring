package com.github.nicolas_kogus.SystemLibrarySpring.Loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ResourceNotFound>  resourceNotFoundResponseEntity (ResourceNotFound exception) {
        ResourceNotFound error = new ResourceNotFound("Resource not found.");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
