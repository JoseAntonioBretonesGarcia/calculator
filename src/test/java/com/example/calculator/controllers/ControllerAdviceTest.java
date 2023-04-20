package com.example.calculator.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.calculator.exceptions.CustomIllegalArgumentException;
import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
@DisplayName("ControllerAdviceTest")
public class ControllerAdviceTest {

    @InjectMocks
    private ControllerAdvice advice;

    @MockBean
    private TracerImpl tracerMock;

    @BeforeEach
    public void init() {
        advice = new ControllerAdvice(tracerMock);
    }

    @Test
    @DisplayName("ControllerAdviceTest-illegalArgumentExceptionHandlerTest")
    public void customIllegalArgumentException() throws Exception {
        CustomIllegalArgumentException exception = new CustomIllegalArgumentException("Los operadores para realizar el cálculo deben ser números.");
        assertEquals(advice.customIllegalArgumentException(exception).getStatusCode().value(), 400);
    }

    @Test
    @DisplayName("ControllerAdviceTest-numberFormatExceptionHandlerTest")
    public void numberFormatExceptionHandlerTest() {
        NumberFormatException exception = new NumberFormatException("Los operadores para realizar el cálculo deben ser números.");
        assertEquals(advice.numberFormatExceptionHandler(exception).getStatusCode().value(), 400);
    }
}