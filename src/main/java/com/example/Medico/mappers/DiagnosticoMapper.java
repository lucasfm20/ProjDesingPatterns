package com.example.Medico.mappers;

import com.example.Medico.dtos.DiagnosticoDTO;
import com.example.Medico.models.Diagnostico;

public class DiagnosticoMapper {

    public static DiagnosticoDTO convertToDTO(Diagnostico diagnostico) {
        DiagnosticoDTO diagnosticoDTO = new DiagnosticoDTO();
        diagnosticoDTO.setId(diagnostico.getId());
        diagnosticoDTO.setPacienteId(diagnostico.getPacienteId());
        diagnosticoDTO.setMedicoId(diagnostico.getMedicoId());
        diagnosticoDTO.setDataHora(diagnostico.getDataHora());
        diagnosticoDTO.setDescricao(diagnostico.getDescricao());
        return diagnosticoDTO;
    }

    public static Diagnostico convertToEntity(DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(diagnosticoDTO.getId());
        diagnostico.setPacienteId(diagnosticoDTO.getPacienteId());
        diagnostico.setMedicoId(diagnosticoDTO.getMedicoId());
        diagnostico.setDataHora(diagnosticoDTO.getDataHora());
        diagnostico.setDescricao(diagnosticoDTO.getDescricao());
        return diagnostico;
    }
}
