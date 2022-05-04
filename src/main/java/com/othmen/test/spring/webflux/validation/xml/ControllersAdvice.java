package com.othmen.test.spring.webflux.validation.xml;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class ControllersAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<List<String>> onWebExchangeBindException(WebExchangeBindException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors().stream()
                        .map(objectError -> objectError.unwrap(ConstraintViolation.class))
                .map(ConstraintViolation::getMessage).toList());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> onConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage()).toList());
    }
}
