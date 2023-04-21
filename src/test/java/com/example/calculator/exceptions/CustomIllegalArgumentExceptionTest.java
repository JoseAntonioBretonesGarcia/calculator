package com.example.calculator.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("CustomIllegalArgumentExceptionTest")
class CustomIllegalArgumentExceptionTest {

    @Mock
    CustomIllegalArgumentException customIllegalArgumentException;

    @Test
    @DisplayName("CustomIllegalArgumentExceptionTest - customIllegalArgumentExceptionTest")
    void customIllegalArgumentExceptionTest() {
        customIllegalArgumentException = new CustomIllegalArgumentException("Probando excepción propia");
    }

}
