package com.example.calculator.controllers;

import com.example.calculator.exceptions.CustomIllegalArgumentException;
import com.example.calculator.interfaces.Operacion;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OperacionController {


    private final List<Operacion> operaciones;
    private final TracerImpl tracer;

    @Autowired
    public OperacionController(List<Operacion> operations,TracerImpl tracer) {
        this.operaciones = operations;
        this.tracer = tracer;
    }


    @GetMapping("/")
    public ResponseEntity<?> ejecutarOperacion(@RequestParam BigDecimal operador1 , @RequestParam BigDecimal operador2, @RequestParam String operacion){
        Optional<Operacion> operacionImpl = operaciones.stream()
                .filter(op ->
                        op.getClass().getSimpleName().equalsIgnoreCase(operacion))
                .findFirst();
        if (operacionImpl.isPresent()) {
            var result = operacionImpl.get().calcular(operador1, operador2).toString();
            tracer.trace(result);
            return new ResponseEntity<>("El resultado es: ".concat(result),HttpStatus.OK);
        }
            throw new CustomIllegalArgumentException("No existe actualmente ese tipo de operación.");
    }
}
