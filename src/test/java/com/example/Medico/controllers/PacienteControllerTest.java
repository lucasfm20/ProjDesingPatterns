package com.example.Medico.controllers;

import com.example.Medico.dtos.PacienteDTO;
import com.example.Medico.services.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PacienteControllerTest {
    @Mock
    private PacienteService pacienteService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private PacienteController pacienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        PacienteDTO p1 = new PacienteDTO();
        PacienteDTO p2 = new PacienteDTO();
        Page<PacienteDTO> page = new PageImpl<>(Arrays.asList(p1, p2));
        when(pacienteService.findAll(any(Pageable.class))).thenReturn(page);
        Page<PacienteDTO> result = pacienteController.findAll(0, 10);
        assertEquals(2, result.getContent().size());
        verify(pacienteService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testFindByIdFound() {
        PacienteDTO p = new PacienteDTO();
        when(pacienteService.findById(1L)).thenReturn(Optional.of(p));
        ResponseEntity<PacienteDTO> response = pacienteController.findById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(p, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(pacienteService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<PacienteDTO> response = pacienteController.findById(1L);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testSaveSuccess() {
        PacienteDTO p = new PacienteDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(pacienteService.save(any(PacienteDTO.class))).thenReturn(p);
        ResponseEntity<?> response = pacienteController.save(p, bindingResult);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(p, response.getBody());
    }

    @Test
    void testSaveValidationError() {
        PacienteDTO p = new PacienteDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList());
        ResponseEntity<?> response = pacienteController.save(p, bindingResult);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testUpdateSuccess() {
        PacienteDTO p = new PacienteDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(pacienteService.update(eq(1L), any(PacienteDTO.class))).thenReturn(Optional.of(p));
        ResponseEntity<?> response = pacienteController.update(1L, p, bindingResult);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(p, response.getBody());
    }

    @Test
    void testUpdateValidationError() {
        PacienteDTO p = new PacienteDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList());
        ResponseEntity<?> response = pacienteController.update(1L, p, bindingResult);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testUpdateNotFound() {
        PacienteDTO p = new PacienteDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(pacienteService.update(eq(1L), any(PacienteDTO.class))).thenReturn(Optional.empty());
        ResponseEntity<?> response = pacienteController.update(1L, p, bindingResult);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteById() {
        doNothing().when(pacienteService).deleteById(1L);
        ResponseEntity<Void> response = pacienteController.deleteById(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(pacienteService, times(1)).deleteById(1L);
    }
}

