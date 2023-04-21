package com.example.calculator.dtos;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ListadoOperacionesDTO {
    public List<String> operacionesDisponibles = new ArrayList<>();
}
