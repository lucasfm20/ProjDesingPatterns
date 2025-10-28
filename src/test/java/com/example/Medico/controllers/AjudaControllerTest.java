package com.example.Medico.controllers;

import com.example.Medico.models.Ajuda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AjudaControllerTest {
    @InjectMocks
    private AjudaController ajudaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAjuda() {
        Ajuda ajudaMock = mock(Ajuda.class);

        Ajuda result = ajudaController.getAjuda();
        assertNotNull(result);
    }
}

