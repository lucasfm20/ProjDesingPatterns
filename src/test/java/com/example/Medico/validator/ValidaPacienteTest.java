package com.example.Medico.validator;

import com.example.Medico.dtos.ConsultaDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidaPacienteTest {
    private final ValidaPaciente validaPaciente = new ValidaPaciente();

    @Test
    void testPacienteIdValido() {
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setPacienteId(1L);
        assertDoesNotThrow(() -> validaPaciente.executarValidacao(consulta));
    }

    @Test
    void testPacienteIdNulo() {
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setPacienteId(null);
        Exception ex = assertThrows(Exception.class, () -> validaPaciente.executarValidacao(consulta));
        assertEquals("Paciente inválido ou não informado.", ex.getMessage());
    }

    @Test
    void testPacienteIdZero() {
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setPacienteId(0L);
        Exception ex = assertThrows(Exception.class, () -> validaPaciente.executarValidacao(consulta));
        assertEquals("Paciente inválido ou não informado.", ex.getMessage());
    }

    @Test
    void testPacienteIdNegativo() {
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setPacienteId(-5L);
        Exception ex = assertThrows(Exception.class, () -> validaPaciente.executarValidacao(consulta));
        assertEquals("Paciente inválido ou não informado.", ex.getMessage());
    }
}

