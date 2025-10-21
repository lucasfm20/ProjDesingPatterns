package com.example.Medico.mappers;

import com.example.Medico.dtos.MedicoDTO;
import com.example.Medico.models.Medico;

public class MedicoMapper {
    public static MedicoDTO convertToDTO(Medico medico) {
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNome(medico.getNome());
        medicoDTO.setEspecialidade(medico.getEspecialidade());
        medicoDTO.setContato(medico.getContato());
        return medicoDTO;
    }

    public static Medico convertToEntity(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setId(medicoDTO.getId());
        medico.setNome(medicoDTO.getNome());
        medico.setEspecialidade(medicoDTO.getEspecialidade());
        medico.setContato(medicoDTO.getContato());
        return medico;
    }
}
