package com.example.calculator.services;

import com.example.calculator.interfaces.Operacion;
import com.example.calculator.models.Suma;
import io.corp.calculator.TracerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@DisplayName("OperacionServiceTest")
@ExtendWith(SpringExtension.class)
class OperacionServiceTest {

    @MockBean
    List<Operacion> operaciones;

    @MockBean
    TracerImpl tracerMock;

    @InjectMocks
    OperacionService operacionService;

    @BeforeEach
    void init() {
        operaciones.add(new Suma());
        operacionService = new OperacionService(operaciones);
    }

    @Test
    @DisplayName("OperacionServiceTest - buscarOperacion")
    void buscarOperacion(){
        operacionService.buscarOperacion("suma");
    }

    @Test
    @DisplayName("OperacionServiceTest - getOperaciones")
    void getOperaciones(){
        operacionService.getOperaciones();
    }

}
