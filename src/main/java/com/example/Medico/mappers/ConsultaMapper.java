package com.example.Medico.mappers;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.models.Consulta;


public class ConsultaMapper {


    public static ConsultaDTO convertToDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setPacienteId(consulta.getPacienteId());
        consultaDTO.setMedicoId(consulta.getMedicoId());
        consultaDTO.setDataHora(consulta.getDataHora());
        consultaDTO.setDescricao(consulta.getDescricao());
        return consultaDTO;
    }

    public static Consulta convertToEntity(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setId(consultaDTO.getId());
        consulta.setPacienteId(consultaDTO.getPacienteId());
        consulta.setMedicoId(consultaDTO.getMedicoId());
        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setDescricao(consultaDTO.getDescricao());
        return consulta;
    }
}
