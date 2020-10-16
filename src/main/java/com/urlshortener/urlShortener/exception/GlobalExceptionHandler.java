package com.urlshortener.urlShortener.exception;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
This class includes handlers of some
specific exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> handleConstraintViolationException(
            ConstraintViolationException exp){

        List<String> errors = new ArrayList<>(exp.getConstraintViolations().size());
        exp.getConstraintViolations().forEach(
                exception -> {
                    errors.add(exception.getPropertyPath() + " : " + exception.getMessage());
                }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodNotValidException(
            MethodArgumentNotValidException exp){

        return new ResponseEntity(exp.getBindingResult(), HttpStatus.BAD_REQUEST);
    }

}
