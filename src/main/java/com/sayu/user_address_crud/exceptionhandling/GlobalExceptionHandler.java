package com.sayu.user_address_crud.exceptionhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        StringBuilder errorMessage = new StringBuilder();
//        ex.getBindingResult().getFieldErrors().
//                forEach(error -> errorMessage.append(error.getDefaultMessage()).append(". "));
//        return ResponseEntity.badRequest().body(errorMessage.toString());
//    }
}
