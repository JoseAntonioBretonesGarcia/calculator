package com.example.calculator.controllers;

import com.example.calculator.exceptions.CustomIllegalArgumentException;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvice {


    private final TracerImpl tracer;

    @Autowired
    public ControllerAdvice(TracerImpl tracer){
        this.tracer = tracer;
    }


    @ExceptionHandler(value = CustomIllegalArgumentException.class)
    public ResponseEntity<?> customIllegalArgumentException(CustomIllegalArgumentException customIllegalArgumentException){
        var error = customIllegalArgumentException.getMessage();
        tracer.trace(error);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<?> numberFormatExceptionHandler(NumberFormatException numberFormatException){
        var error = "Los operadores para realizar el cálculo deben ser números.";
        tracer.trace(error);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
