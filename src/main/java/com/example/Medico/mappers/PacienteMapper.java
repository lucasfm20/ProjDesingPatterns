package com.example.Medico.mappers;

import com.example.Medico.dtos.PacienteDTO;
import com.example.Medico.models.Paciente;

public class PacienteMapper {
    public static PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setDataDeNascimento(paciente.getDataDeNascimento());
        pacienteDTO.setContato(paciente.getContato());
        return pacienteDTO;
    }

    public static Paciente convertToEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setNome(pacienteDTO.getNome());
        paciente.setDataDeNascimento(pacienteDTO.getDataDeNascimento());
        paciente.setContato(pacienteDTO.getContato());
        return paciente;
    }
}
