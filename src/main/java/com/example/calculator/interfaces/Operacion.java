package com.example.calculator.interfaces;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface Operacion {
    BigDecimal calcular(BigDecimal operator1 , BigDecimal operator2);
}
