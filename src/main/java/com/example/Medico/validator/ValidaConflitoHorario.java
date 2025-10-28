package com.example.Medico.validator;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaConflitoHorario extends ValidadorConsulta {

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    protected void executarValidacao(ConsultaDTO consultaDTO) throws Exception {
        boolean existe = consultaRepository.existsByMedicoIdAndDataHora(
                consultaDTO.getMedicoId(), consultaDTO.getDataHora()
        );
        if (existe) {
            throw new Exception("Já existe uma consulta para esse médico nesse horário.");
        }
    }
}
