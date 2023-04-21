package com.example.calculator.controllers;

import com.example.calculator.exceptions.CustomIllegalArgumentException;
import com.example.calculator.interfaces.Operacion;
import com.example.calculator.models.Suma;
import com.example.calculator.services.OperacionService;
import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@DisplayName("OperacionControllerTest")
@ExtendWith(SpringExtension.class)
class OperacionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TracerImpl tracerMock;

    @MockBean
    List<Operacion> operaciones;

    @MockBean
    OperacionService operacionService;

    @MockBean
    ControllerAdvice controllerAdvice;


    @Test
    @DisplayName("OperacionControllerTest-ejecutarListadoOperacionesTest")
    void ejecutarListadoOperacionesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculadora/listado/operaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaValidaTest")
    void ejecutarOperacionSumaValidaTest() throws Exception {
        Suma suma = new Suma();
        when(operaciones.stream()).thenReturn(Stream.of(suma));
        mockMvc.perform(MockMvcRequestBuilders.get("/calculadora/operacion/")
                        .param("operador1","1")
                        .param("operador2","1")
                        .param("operacion","suma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaNoValidaTest")
    void ejecutarOperacionSumaNoValidaTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculadora/operacion/")
                        .param("operador1", "1")
                        .param("operador2", "1")
                        .param("operacion", "sumaa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomIllegalArgumentException));
    }

    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaOperador1NoValidoTest")
    void ejecutarOperacionSumaOperador1NoValidoTest() throws Exception {
        Suma suma = new Suma();
        when(operaciones.stream()).thenReturn(Stream.of(suma));
        mockMvc.perform(MockMvcRequestBuilders.get("/calculadora/operacion/")
                        .param("operador1","h")
                        .param("operador2","1")
                        .param("operacion","suma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }

    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaOperador2NoValidoTest")
    void ejecutarOperacionSumaOperador2NoValidoTest() throws Exception {
        Suma suma = new Suma();
        when(operaciones.stream()).thenReturn(Stream.of(suma));
        mockMvc.perform(MockMvcRequestBuilders.get("/calculadora/operacion/")
                        .param("operador1","1")
                        .param("operador2","t")
                        .param("operacion","suma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }
}
