package com.example.Medico.services;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.mappers.ConsultaMapper;
import com.example.Medico.models.Consulta;
import com.example.Medico.repository.ConsultaRepository;
import com.example.Medico.validator.ValidaConflitoHorario;
import com.example.Medico.validator.ValidaPaciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ConsultaServiceTest {
    @Mock
    private ConsultaRepository consultaRepository;
    @Mock
    private ValidaPaciente validaPaciente;
    @Mock
    private ValidaConflitoHorario validaConflitoHorario;
    @InjectMocks
    private ConsultaService consultaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Consulta consulta = new Consulta();
        ConsultaDTO consultaDTO = new ConsultaDTO();
        Page<Consulta> page = new PageImpl<>(Arrays.asList(consulta));
        when(consultaRepository.findAll(any(Pageable.class))).thenReturn(page);
        mockStatic(ConsultaMapper.class).when(() -> ConsultaMapper.convertToDTO(any(Consulta.class))).thenReturn(consultaDTO);
        Page<ConsultaDTO> result = consultaService.findAll(Pageable.unpaged());
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testFindByIdFound() {
        Consulta consulta = new Consulta();
        ConsultaDTO consultaDTO = new ConsultaDTO();
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consulta));
        mockStatic(ConsultaMapper.class).when(() -> ConsultaMapper.convertToDTO(consulta)).thenReturn(consultaDTO);
        Optional<ConsultaDTO> result = consultaService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(consultaDTO, result.get());
    }

    @Test
    void testFindByIdNotFound() {
        when(consultaRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<ConsultaDTO> result = consultaService.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void testSaveSuccess() throws Exception {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        Consulta consulta = new Consulta();
        ConsultaDTO savedDTO = new ConsultaDTO();
        doNothing().when(validaPaciente).setProximo(validaConflitoHorario);
        doNothing().when(validaPaciente).validar(consultaDTO);
        mockStatic(ConsultaMapper.class).when(() -> ConsultaMapper.convertToEntity(consultaDTO)).thenReturn(consulta);
        when(consultaRepository.save(consulta)).thenReturn(consulta);
        mockStatic(ConsultaMapper.class).when(() -> ConsultaMapper.convertToDTO(consulta)).thenReturn(savedDTO);
        ConsultaDTO result = consultaService.save(consultaDTO);
        assertEquals(savedDTO, result);
    }

    @Test
    void testSaveValidationError() throws Exception {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        doNothing().when(validaPaciente).setProximo(validaConflitoHorario);
        doThrow(new Exception("erro"))
                .when(validaPaciente).validar(consultaDTO);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> consultaService.save(consultaDTO));
        assertTrue(ex.getMessage().contains("Erro na validação: erro"));
    }

    @Test
    void testDeleteById() {
        doNothing().when(consultaRepository).deleteById(1L);
        consultaService.deleteById(1L);
        verify(consultaRepository, times(1)).deleteById(1L);
    }
}

