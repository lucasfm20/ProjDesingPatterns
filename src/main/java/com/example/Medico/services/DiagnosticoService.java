package com.example.Medico.services;

import com.example.Medico.dtos.DiagnosticoDTO;
import com.example.Medico.mappers.DiagnosticoMapper;
import com.example.Medico.models.Diagnostico;
import com.example.Medico.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    //Consutar todos os diagnósticos
    public List<DiagnosticoDTO> findAll() {
        return diagnosticoRepository.findAll()
                .stream()
                .map(DiagnosticoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    //Consultar apenas um diagnóstico
    public Optional<DiagnosticoDTO> findById(Long id) {
        return diagnosticoRepository.findById(id)
                .map(DiagnosticoMapper::convertToDTO);
    }

    //Salvar o diagnóstico criado
    public DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = DiagnosticoMapper.convertToEntity(diagnosticoDTO);
        return DiagnosticoMapper.convertToDTO(diagnosticoRepository.save(diagnostico));
    }

    //Deletar diagnóstico
    public void deleteById(Long id) {diagnosticoRepository.deleteById(id);}
}