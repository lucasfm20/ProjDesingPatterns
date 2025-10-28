package com.example.Medico.controllers;

import com.example.Medico.dtos.TratamentoDTO;
import com.example.Medico.services.TratamentoService;
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

class TratamentoControllerTest {
    @Mock
    private TratamentoService tratamentoService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private TratamentoController tratamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        TratamentoDTO t1 = new TratamentoDTO();
        TratamentoDTO t2 = new TratamentoDTO();
        List<TratamentoDTO> list = Arrays.asList(t1, t2);
        when(tratamentoService.findAll()).thenReturn(list);
        List<TratamentoDTO> result = tratamentoController.findAll();
        assertEquals(2, result.size());
        verify(tratamentoService, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        TratamentoDTO t = new TratamentoDTO();
        when(tratamentoService.findById(1L)).thenReturn(Optional.of(t));
        ResponseEntity<TratamentoDTO> response = tratamentoController.findById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(t, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(tratamentoService.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<TratamentoDTO> response = tratamentoController.findById(1L);
        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void testSaveSuccess() {
        TratamentoDTO t = new TratamentoDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(tratamentoService.save(any(TratamentoDTO.class))).thenReturn(t);
        ResponseEntity<?> response = tratamentoController.save(t, bindingResult);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(t, response.getBody());
    }

    @Test
    void testSaveValidationError() {
        TratamentoDTO t = new TratamentoDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(List.of());
        ResponseEntity<?> response = tratamentoController.save(t, bindingResult);
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void testDeleteById() {
        doNothing().when(tratamentoService).deleteById(1L);
        ResponseEntity<Void> response = tratamentoController.deleteById(1L);
        assertEquals(204, response.getStatusCode().value());
        verify(tratamentoService, times(1)).deleteById(1L);
    }
}

