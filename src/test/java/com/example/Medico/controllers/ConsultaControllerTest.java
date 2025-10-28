package com.example.Medico.controllers;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.services.ConsultaService;
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

class ConsultaControllerTest {
    @Mock
    private ConsultaService consultaService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ConsultaController consultaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        ConsultaDTO consulta1 = new ConsultaDTO();
        ConsultaDTO consulta2 = new ConsultaDTO();
        Page<ConsultaDTO> page = new PageImpl<>(Arrays.asList(consulta1, consulta2));
        when(consultaService.findAll(any(Pageable.class))).thenReturn(page);

        Page<ConsultaDTO> result = consultaController.findAll(0, 10);
        assertEquals(2, result.getContent().size());
        verify(consultaService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testFindByIdFound() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(consultaService.findById(1L)).thenReturn(Optional.of(consulta));

        ResponseEntity<ConsultaDTO> response = consultaController.findById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(consulta, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(consultaService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ConsultaDTO> response = consultaController.findById(1L);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testSaveSuccess() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(consultaService.save(any(ConsultaDTO.class))).thenReturn(consulta);

        ResponseEntity<?> response = consultaController.save(consulta, bindingResult);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(consulta, response.getBody());
    }

    @Test
    void testSaveValidationError() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList());

        ResponseEntity<?> response = consultaController.save(consulta, bindingResult);
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void testSaveException() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(consultaService.save(any(ConsultaDTO.class))).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<?> response = consultaController.save(consulta, bindingResult);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro", response.getBody());
    }

    @Test
    void testDeleteById() {
        doNothing().when(consultaService).deleteById(1L);
        ResponseEntity<Void> response = consultaController.deleteById(1L);
        assertEquals(204, response.getStatusCodeValue());
        verify(consultaService, times(1)).deleteById(1L);
    }
}

