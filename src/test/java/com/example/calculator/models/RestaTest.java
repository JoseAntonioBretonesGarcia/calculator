package com.example.calculator.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("RestaTest")
public class RestaTest {

    Resta resta = new Resta();

    @Test
    @DisplayName("RestaTest-calcularTest")
    public void calcularTest(){
        assertEquals(resta.calcular(new BigDecimal(2),new BigDecimal(1)),new BigDecimal(1));
    }
}
