package com.example.calculator.controllers;

import com.example.calculator.interfaces.Operacion;
import com.example.calculator.models.Suma;
import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@DisplayName("OperacionControllerTest")
public class OperacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TracerImpl tracerMock;

    @Mock
    private List<Operacion> operaciones;

    @InjectMocks
    private OperacionController controller;

    @BeforeEach
    public void init() {
        controller = new OperacionController(operaciones, tracerMock);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaValidaTest")
    public void ejecutarOperacionSumaValidaTest() throws Exception {
        Suma suma = new Suma();
        when(operaciones.stream()).thenReturn(Stream.of(suma));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/")
                        .param("operador1","1")
                        .param("operador2","1")
                        .param("operacion","suma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaNoValidaTest")
    public void ejecutarOperacionSumaNoValidaTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/")
                        .param("operador1", "1")
                        .param("operador2", "1")
                        .param("operacion", "sumaa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaOperador1NoValidoTest")
    public void ejecutarOperacionSumaOperador1NoValidoTest() throws Exception {
        Suma suma = new Suma();
        when(operaciones.stream()).thenReturn(Stream.of(suma));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/")
                        .param("operador1","h")
                        .param("operador2","1")
                        .param("operacion","suma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("OperacionControllerTest-ejecutarOperacionSumaOperador2NoValidoTest")
    public void ejecutarOperacionSumaOperador2NoValidoTest() throws Exception {
        Suma suma = new Suma();
        when(operaciones.stream()).thenReturn(Stream.of(suma));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/")
                        .param("operador1","1")
                        .param("operador2","t")
                        .param("operacion","suma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
