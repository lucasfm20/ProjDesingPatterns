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

    //Buscar todos os tratamentos
    public List<TratamentoDTO> findAll() {
        return tratamentoRepository.findAll().stream().map(TratamentoMapper::convertToDTO).collect(Collectors.toList());
    }

    //Encontrar tratamento pelo id
    public Optional<TratamentoDTO> findById(Long id) {
        return tratamentoRepository.findById(id).map(TratamentoMapper::convertToDTO);
    }

    //Criar novo tratamento
    public TratamentoDTO save(TratamentoDTO tratamentoDTO) {
        Tratamento tratamento = TratamentoMapper.convertToEntity(tratamentoDTO);
        return TratamentoMapper.convertToDTO(tratamentoRepository.save(tratamento));
    }

    //Deletar tratamento
    public void deleteById(Long id) {
        tratamentoRepository.deleteById(id);
    }

}
