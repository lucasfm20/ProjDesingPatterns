package com.example.Medico.controllers;

import com.example.Medico.dtos.MedicoDTO;
import com.example.Medico.services.MedicoService;
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

class MedicoControllerTest {
    @Mock
    private MedicoService medicoService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private MedicoController medicoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        MedicoDTO m1 = new MedicoDTO();
        MedicoDTO m2 = new MedicoDTO();
        List<MedicoDTO> list = Arrays.asList(m1, m2);
        when(medicoService.findAll()).thenReturn(list);
        List<MedicoDTO> result = medicoController.findAll();
        assertEquals(2, result.size());
        verify(medicoService, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        MedicoDTO m = new MedicoDTO();
        when(medicoService.findById(1L)).thenReturn(Optional.of(m));
        ResponseEntity<MedicoDTO> response = medicoController.findById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(m, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(medicoService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<MedicoDTO> response = medicoController.findById(1L);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testSaveSuccess() {
        MedicoDTO m = new MedicoDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(medicoService.save(any(MedicoDTO.class))).thenReturn(m);
        ResponseEntity<?> response = medicoController.save(m, bindingResult);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(m, response.getBody());
    }

    @Test
    void testSaveValidationError() {
        MedicoDTO m = new MedicoDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList());
        ResponseEntity<?> response = medicoController.save(m, bindingResult);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testDeleteById() {
        doNothing().when(medicoService).deleteById(1L);
        ResponseEntity<Void> response = medicoController.deleteById(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(medicoService, times(1)).deleteById(1L);
    }
}

