package com.example.Medico.validator;

import com.example.Medico.dtos.ConsultaDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidaPaciente extends ValidadorConsulta {

    //Validar paciente para não cadastrar incorretamente

    @Override
    protected void executarValidacao(ConsultaDTO consultaDTO) throws Exception {
        if (consultaDTO.getPacienteId() == null || consultaDTO.getPacienteId() <= 0) {
            throw new Exception("Paciente inválido ou não informado.");
        }
    }
}


