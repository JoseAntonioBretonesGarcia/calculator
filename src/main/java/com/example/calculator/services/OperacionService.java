package com.example.calculator.services;

import com.example.calculator.interfaces.Operacion;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OperacionService {

    @Autowired
    List<Operacion> operaciones;

    public Optional<Operacion> buscarOperacion(String operacion){
        return  operaciones.stream()
                .filter(op ->
                        op.getClass().getSimpleName().equalsIgnoreCase(operacion))
                .findFirst();
    }

    public List<String> getOperaciones(){
        List<String> operacionesDisponibles = new ArrayList<>();
        operaciones.forEach(op ->operacionesDisponibles.add(op.getClass().getSimpleName()));
        return operacionesDisponibles;
    }
}
