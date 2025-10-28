package com.example.Medico.validator;

import com.example.Medico.dtos.ConsultaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorConsultaTest {
    private static class DummyValidador extends ValidadorConsulta {
        private boolean validated = false;
        private final boolean shouldThrow;

        public DummyValidador(boolean shouldThrow) { this.shouldThrow = shouldThrow; }

        @Override
        protected void executarValidacao(ConsultaDTO consultaDTO) throws Exception {
            validated = true;
            if (shouldThrow) throw new Exception("Erro de validação");
        }

        public boolean isValidated() { return validated; }
    }

    private ConsultaDTO consultaDTO;

    @BeforeEach
    void setUp() {
        consultaDTO = new ConsultaDTO();
    }

    @Test
    void testValidarChamaExecutarValidacao() throws Exception {
        DummyValidador validador = new DummyValidador(false);
        validador.validar(consultaDTO);
        assertTrue(validador.isValidated());
    }

    @Test
    void testValidarEncadeamento() throws Exception {
        DummyValidador v1 = new DummyValidador(false);
        DummyValidador v2 = new DummyValidador(false);
        v1.setProximo(v2);
        v1.validar(consultaDTO);
        assertTrue(v1.isValidated());
        assertTrue(v2.isValidated());
    }

    @Test
    void testValidarLancaExcecao() {
        DummyValidador validador = new DummyValidador(true);
        Exception ex = assertThrows(Exception.class, () -> validador.validar(consultaDTO));
        assertEquals("Erro de validação", ex.getMessage());
    }

    @Test
    void testValidarParaNoPrimeiroErro() {
        DummyValidador v1 = new DummyValidador(true);
        DummyValidador v2 = new DummyValidador(false);
        v1.setProximo(v2);
        Exception ex = assertThrows(Exception.class, () -> v1.validar(consultaDTO));
        assertEquals("Erro de validação", ex.getMessage());
        assertTrue(v1.isValidated());
        assertFalse(v2.isValidated());
    }
}

