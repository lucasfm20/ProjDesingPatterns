package com.example.Medico.controllers;

import com.example.Medico.dtos.DiagnosticoDTO;
import com.example.Medico.services.DiagnosticoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DiagnosticoControllerTest {
    @Mock
    private DiagnosticoService diagnosticoService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private DiagnosticoController diagnosticoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        DiagnosticoDTO d1 = new DiagnosticoDTO();
        DiagnosticoDTO d2 = new DiagnosticoDTO();
        List<DiagnosticoDTO> list = Arrays.asList(d1, d2);
        when(diagnosticoService.findAll()).thenReturn(list);
        List<DiagnosticoDTO> result = diagnosticoController.findAll();
        assertEquals(2, result.size());
        verify(diagnosticoService, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        DiagnosticoDTO d = new DiagnosticoDTO();
        when(diagnosticoService.findById(1L)).thenReturn(Optional.of(d));
        ResponseEntity<DiagnosticoDTO> response = diagnosticoController.findById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(d, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(diagnosticoService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<DiagnosticoDTO> response = diagnosticoController.findById(1L);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testSaveSuccess() {
        DiagnosticoDTO d = new DiagnosticoDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(diagnosticoService.save(any(DiagnosticoDTO.class))).thenReturn(d);
        ResponseEntity<?> response = diagnosticoController.save(d, bindingResult);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(d, response.getBody());
    }

    @Test
    void testSaveValidationError() {
        DiagnosticoDTO d = new DiagnosticoDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList());
        ResponseEntity<?> response = diagnosticoController.save(d, bindingResult);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testDeleteById() {
        doNothing().when(diagnosticoService).deleteById(1L);
        ResponseEntity<Void> response = diagnosticoController.deleteById(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(diagnosticoService, times(1)).deleteById(1L);
    }
}

