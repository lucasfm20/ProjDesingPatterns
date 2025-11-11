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
        ResponseEntity<?> response = medicoController.findAll();
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody() instanceof List);
        assertEquals(2, ((List<?>) response.getBody()).size());
        verify(medicoService, times(1)).findAll();
    }

    @Test
    void testFindAllEmpty() {
        when(medicoService.findAll()).thenReturn(List.of());
        ResponseEntity<?> response = medicoController.findAll();
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Nenhum médico cadastrado.", response.getBody());
    }

    @Test
    void testFindByIdFound() {
        MedicoDTO m = new MedicoDTO();
        when(medicoService.findById(1L)).thenReturn(Optional.of(m));
        ResponseEntity<?> response = medicoController.findById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(m, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(medicoService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<?> response = medicoController.findById(1L);
        assertEquals(404, response.getStatusCode().value());
        assertEquals("Médico não encontrado.", response.getBody());
    }

    @Test
    void testSaveSuccess() {
        MedicoDTO m = new MedicoDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(medicoService.save(any(MedicoDTO.class))).thenReturn(m);
        ResponseEntity<?> response = medicoController.save(m, bindingResult);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(m, response.getBody());
    }

    @Test
    void testSaveValidationError() {
        MedicoDTO m = new MedicoDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList());
        ResponseEntity<?> response = medicoController.save(m, bindingResult);
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void testDeleteById() {
        doNothing().when(medicoService).deleteById(1L);
        ResponseEntity<String> response = medicoController.deleteById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Médico removido com sucesso.", response.getBody());
        verify(medicoService, times(1)).deleteById(1L);
    }
}

