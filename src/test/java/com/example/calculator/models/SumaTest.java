package com.example.calculator.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SumaTest")
public class SumaTest {

    Suma suma = new Suma();

    @Test
    @DisplayName("SumaTest-calcularTest")
    public void calcularTest(){
        assertEquals(suma.calcular(new BigDecimal(1),new BigDecimal(1)),new BigDecimal(2));
    }
}
