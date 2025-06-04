package com.example.Medico.validator;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.validator.ValidadorConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidaPaciente extends ValidadorConsulta {

    @Override
    protected void executarValidacao(ConsultaDTO consultaDTO) throws Exception {
        if (consultaDTO.getPacienteId() == null || consultaDTO.getPacienteId() <= 0) {
            throw new Exception("Paciente inválido ou não informado.");
        }
    }
}


