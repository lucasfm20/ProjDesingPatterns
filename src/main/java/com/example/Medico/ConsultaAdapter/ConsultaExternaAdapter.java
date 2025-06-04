package com.example.Medico.ConsultaAdapter;

import com.example.Medico.ConsultaAdapter.ConsultaExternaDTO;
import com.example.Medico.dtos.ConsultaDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsultaExternaAdapter {

    public static ConsultaDTO adaptar(ConsultaExternaDTO externaDTO) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(externaDTO.getIdConsulta());
        dto.setPacienteId(externaDTO.getIdPaciente());
        dto.setMedicoId(externaDTO.getIdMedico());

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        dto.setDataHora(LocalDateTime.parse(externaDTO.getData(), formatter));

        dto.setDescricao(externaDTO.getDetalhes());
        return dto;
    }
}
