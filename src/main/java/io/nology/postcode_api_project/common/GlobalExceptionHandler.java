package io.nology.postcode_api_project.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.nology.postcode_api_project.common.exceptions.ConflictExceptions;
import io.nology.postcode_api_project.common.exceptions.NotFoundException;
@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictExceptions.class)
    public ResponseEntity<String> handleConflictException(ConflictExceptions ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); // 409 Conflict
    }

}
