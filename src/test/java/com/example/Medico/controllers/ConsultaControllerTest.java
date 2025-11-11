package com.example.Medico.controllers;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.exceptions.ConsultaNotFoundException;
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

        ResponseEntity<?> response = consultaController.findAll(0, 10);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody() instanceof Page);
        assertEquals(2, ((Page<?>) response.getBody()).getContent().size());
        verify(consultaService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testFindAllEmpty() {
        when(consultaService.findAll(any(Pageable.class))).thenReturn(Page.empty());
        ResponseEntity<?> response = consultaController.findAll(0, 10);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Nenhuma consulta cadastrada.", response.getBody());
    }

    @Test
    void testFindByIdFound() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(consultaService.findById(1L)).thenReturn(consulta);
        ResponseEntity<?> response = consultaController.findById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(consulta, response.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        when(consultaService.findById(1L)).thenThrow(new ConsultaNotFoundException("Consulta não encontrada"));
        assertThrows(ConsultaNotFoundException.class, () -> consultaController.findById(1L));
    }

    @Test
    void testSaveSuccess() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(consultaService.save(any(ConsultaDTO.class))).thenReturn(consulta);

        ResponseEntity<?> response = consultaController.save(consulta, bindingResult);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(consulta, response.getBody());
    }

    @Test
    void testSaveValidationError() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList());

        ResponseEntity<?> response = consultaController.save(consulta, bindingResult);
        assertEquals(400, response.getStatusCode().value());
    }

    @Test
    void testSaveException() {
        ConsultaDTO consulta = new ConsultaDTO();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(consultaService.save(any(ConsultaDTO.class))).thenThrow(new RuntimeException("Erro"));

        ResponseEntity<?> response = consultaController.save(consulta, bindingResult);
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Erro", response.getBody());
    }

    @Test
    void testDeleteById() {
        doNothing().when(consultaService).deleteById(1L);
        ResponseEntity<String> response = consultaController.deleteById(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Consulta removida com sucesso.", response.getBody());
        verify(consultaService, times(1)).deleteById(1L);
    }
}

