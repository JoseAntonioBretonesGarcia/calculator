package com.example.calculator.controllers;

import com.example.calculator.constants.MensajesError;
import com.example.calculator.dtos.ListadoOperacionesDTO;
import com.example.calculator.dtos.ResultadoOperacionDTO;
import com.example.calculator.exceptions.CustomIllegalArgumentException;
import com.example.calculator.interfaces.Operacion;
import com.example.calculator.services.OperacionService;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/calculadora")
public class OperacionController {


    @Autowired
    TracerImpl tracer;

    @Autowired
    OperacionService operacionService;

    @GetMapping("/listado/operaciones")
    public ResponseEntity<ListadoOperacionesDTO> getOperaciones(){
        ListadoOperacionesDTO listadoOperacionesDTO =new ListadoOperacionesDTO(operacionService.getOperaciones());
        tracer.trace(listadoOperacionesDTO.operacionesDisponibles);
        return new ResponseEntity<>(listadoOperacionesDTO,HttpStatus.OK);
    }
    @GetMapping("/operacion/")
    public ResponseEntity<ResultadoOperacionDTO> ejecutarOperacion(@RequestParam BigDecimal operador1 , @RequestParam BigDecimal operador2, @RequestParam String operacion){
        Optional<Operacion> operacionImpl = operacionService.buscarOperacion(operacion);
        operacionImpl.orElseThrow(() ->  new CustomIllegalArgumentException(MensajesError.OPERACION_NO_DISPONIBLE));
        var resultado = operacionImpl.get().calcular(operador1, operador2).toString();
        ResultadoOperacionDTO resultadoOperacionDTO = new ResultadoOperacionDTO(resultado) ;
        tracer.trace(resultado);
        return new ResponseEntity<>(resultadoOperacionDTO,HttpStatus.OK);
    }
}
