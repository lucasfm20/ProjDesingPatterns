package com.example.Medico.services;

import com.example.Medico.dtos.TratamentoDTO;
import com.example.Medico.mappers.TratamentoMapper;
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
        return tratamentos.stream().map(TratamentoMapper::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TratamentoDTO> findById(Long id) {
        Optional<Tratamento> tratamento = tratamentoRepository.findById(id);
        return tratamento.map(TratamentoMapper::convertToDTO);
    }

    public TratamentoDTO save(TratamentoDTO tratamentoDTO) {
        Tratamento tratamento = TratamentoMapper.convertToEntity(tratamentoDTO);
        tratamento = tratamentoRepository.save(tratamento);
        return TratamentoMapper.convertToDTO(tratamento);
    }

    public void deleteById(Long id) {
        tratamentoRepository.deleteById(id);
    }

}
