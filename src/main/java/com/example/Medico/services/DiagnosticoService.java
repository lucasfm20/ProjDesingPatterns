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

    public List<DiagnosticoDTO> findAll() {
        List<Diagnostico> diagnosticos = diagnosticoRepository.findAll();
        return diagnosticos.stream().map(DiagnosticoMapper::convertToDTO).collect(Collectors.toList());
    }

    public Optional<DiagnosticoDTO> findById(Long id) {
        Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(id);
        return diagnostico.map(DiagnosticoMapper::convertToDTO);
    }

    public DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = DiagnosticoMapper.convertToEntity(diagnosticoDTO);
        diagnostico = diagnosticoRepository.save(diagnostico);
        return DiagnosticoMapper.convertToDTO(diagnostico);
    }

    public void deleteById(Long id) {
        diagnosticoRepository.deleteById(id);
    }


}
