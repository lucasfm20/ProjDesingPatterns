package com.example.Medico.validator;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

class ValidaConflitoHorarioTest {
    private ConsultaRepository consultaRepository;
    private ValidaConflitoHorario validaConflitoHorario;

    @BeforeEach
    void setUp() {
        consultaRepository = mock(ConsultaRepository.class);
        validaConflitoHorario = new ValidaConflitoHorario();

        // Injetar o mock manualmente, pois @Autowired não funciona em teste unitário puro

        validaConflitoHorario.consultaRepository = consultaRepository;
    }

    @Test
    void testNaoExisteConflito() {
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setMedicoId(1L);
        consulta.setDataHora(LocalDateTime.now().plusDays(1));
        when(consultaRepository.existsByMedicoIdAndDataHora(1L, consulta.getDataHora())).thenReturn(false);
        assertDoesNotThrow(() -> validaConflitoHorario.executarValidacao(consulta));
    }

    @Test
    void testExisteConflito() {
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setMedicoId(2L);
        consulta.setDataHora(LocalDateTime.now().plusDays(2));
        when(consultaRepository.existsByMedicoIdAndDataHora(2L, consulta.getDataHora())).thenReturn(true);
        Exception ex = assertThrows(Exception.class, () -> validaConflitoHorario.executarValidacao(consulta));
        assertEquals("Já existe uma consulta para esse médico nesse horário.", ex.getMessage());
    }
}

