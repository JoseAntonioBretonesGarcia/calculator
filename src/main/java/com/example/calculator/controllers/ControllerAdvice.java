package com.example.calculator.controllers;

import com.example.calculator.constants.MensajesError;
import com.example.calculator.dtos.ResultadoOperacionDTO;
import com.example.calculator.exceptions.CustomIllegalArgumentException;
import io.corp.calculator.TracerImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@AllArgsConstructor
public class ControllerAdvice {

    @Autowired
    TracerImpl tracer;

    @ExceptionHandler(value = CustomIllegalArgumentException.class)
    public ResponseEntity<ResultadoOperacionDTO> customIllegalArgumentException(CustomIllegalArgumentException customIllegalArgumentException){
        ResultadoOperacionDTO resultadoOperacionDTO = new ResultadoOperacionDTO(customIllegalArgumentException.getMessage()) ;
        tracer.trace(resultadoOperacionDTO.resultado);
        return new ResponseEntity<>(resultadoOperacionDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ResultadoOperacionDTO> numberFormatExceptionHandler(){
        ResultadoOperacionDTO resultadoOperacionDTO = new ResultadoOperacionDTO(MensajesError.OPERADORES_NO_VALIDOS);
        tracer.trace(resultadoOperacionDTO.resultado);
        return new ResponseEntity<>(resultadoOperacionDTO,HttpStatus.BAD_REQUEST);
    }
}
