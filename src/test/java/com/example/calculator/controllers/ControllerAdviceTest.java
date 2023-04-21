package com.example.calculator.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.calculator.constants.MensajesError;
import com.example.calculator.exceptions.CustomIllegalArgumentException;
import com.example.calculator.interfaces.Operacion;
import com.example.calculator.services.OperacionService;
import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@WebMvcTest
@DisplayName("ControllerAdviceTest")
@ExtendWith(SpringExtension.class)
class ControllerAdviceTest {

    @InjectMocks
    ControllerAdvice advice;

    @MockBean
    TracerImpl tracerMock;

    @MockBean
    List<Operacion> operaciones;

    @MockBean
    OperacionService operacionService;


    @BeforeEach
    void init() {
        advice = new ControllerAdvice(tracerMock);
    }

    @Test
    @DisplayName("ControllerAdviceTest-illegalArgumentExceptionHandlerTest")
    void customIllegalArgumentException() throws Exception {
        CustomIllegalArgumentException exception = new CustomIllegalArgumentException(MensajesError.OPERACION_NO_DISPONIBLE);
        assertEquals(advice.customIllegalArgumentException(exception).getStatusCode().value(), 400);
    }

    @Test
    @DisplayName("ControllerAdviceTest-numberFormatExceptionHandlerTest")
    void numberFormatExceptionHandlerTest() {
        assertEquals(advice.numberFormatExceptionHandler().getStatusCode().value(), 400);
    }
}