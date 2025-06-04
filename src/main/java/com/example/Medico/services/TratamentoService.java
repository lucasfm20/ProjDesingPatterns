package com.example.Medico.services;

import com.example.Medico.dtos.TratamentoDTO;
import com.example.Medico.models.Tratamento;
import com.example.Medico.repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    public List<TratamentoDTO> findAll() {
        List<Tratamento> tratamentos = tratamentoRepository.findAll();
        return tratamentos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TratamentoDTO> findById(Long id) {
        Optional<Tratamento> tratamento = tratamentoRepository.findById(id);
        return tratamento.map(this::convertToDTO);
    }

    public TratamentoDTO save(TratamentoDTO tratamentoDTO) {
        Tratamento tratamento = convertToEntity(tratamentoDTO);
        tratamento = tratamentoRepository.save(tratamento);
        return convertToDTO(tratamento);
    }

    public void deleteById(Long id) {
        tratamentoRepository.deleteById(id);
    }

    private TratamentoDTO convertToDTO(Tratamento tratamento) {
        TratamentoDTO dto = new TratamentoDTO();
        dto.setId(tratamento.getId());
        dto.setDiagnosticoId(tratamento.getDiagnosticoId());
        dto.setMedicamentoId(tratamento.getMedicamentoId());
        dto.setDescricao(tratamento.getDescricao());
        dto.setDuracao(tratamento.getDuracao());
        return dto;
    }

    private Tratamento convertToEntity(TratamentoDTO dto) {
        Tratamento tratamento = new Tratamento();
        tratamento.setId(dto.getId());
        tratamento.setDiagnosticoId(dto.getDiagnosticoId());
        tratamento.setMedicamentoId(dto.getMedicamentoId());
        tratamento.setDescricao(dto.getDescricao());
        tratamento.setDuracao(dto.getDuracao());
        return tratamento;
    }
}
