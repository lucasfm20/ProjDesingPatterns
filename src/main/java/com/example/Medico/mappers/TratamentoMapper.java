package com.example.Medico.mappers;

import com.example.Medico.dtos.TratamentoDTO;
import com.example.Medico.models.Tratamento;

public class TratamentoMapper {

    public static TratamentoDTO convertToDTO(Tratamento tratamento) {
        TratamentoDTO dto = new TratamentoDTO();
        dto.setId(tratamento.getId());
        dto.setDiagnosticoId(tratamento.getDiagnosticoId());
        dto.setMedicamentoId(tratamento.getMedicamentoId());
        dto.setDescricao(tratamento.getDescricao());
        dto.setDuracao(tratamento.getDuracao());
        return dto;
    }

    public static Tratamento convertToEntity(TratamentoDTO dto) {
        Tratamento tratamento = new Tratamento();
        tratamento.setId(dto.getId());
        tratamento.setDiagnosticoId(dto.getDiagnosticoId());
        tratamento.setMedicamentoId(dto.getMedicamentoId());
        tratamento.setDescricao(dto.getDescricao());
        tratamento.setDuracao(dto.getDuracao());
        return tratamento;
    }
}
