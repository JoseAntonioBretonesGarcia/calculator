package com.example.calculator.models;

import com.example.calculator.interfaces.Operacion;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@NoArgsConstructor
@Component("SumaComponent")
public class Suma implements Operacion {
    @Override
    public BigDecimal calcular(BigDecimal operator1, BigDecimal operator2) {
        return operator1.add(operator2);
    }
}
