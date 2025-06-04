package com.example.Medico.services;

import com.example.Medico.dtos.DiagnosticoDTO;
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
        return diagnosticos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<DiagnosticoDTO> findById(Long id) {
        Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(id);
        return diagnostico.map(this::convertToDTO);
    }

    public DiagnosticoDTO save(DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = convertToEntity(diagnosticoDTO);
        diagnostico = diagnosticoRepository.save(diagnostico);
        return convertToDTO(diagnostico);
    }

    public void deleteById(Long id) {
        diagnosticoRepository.deleteById(id);
    }

    private DiagnosticoDTO convertToDTO(Diagnostico diagnostico) {
        DiagnosticoDTO diagnosticoDTO = new DiagnosticoDTO();
        diagnosticoDTO.setId(diagnostico.getId());
        diagnosticoDTO.setPacienteId(diagnostico.getPacienteId());
        diagnosticoDTO.setMedicoId(diagnostico.getMedicoId());
        diagnosticoDTO.setDataHora(diagnostico.getDataHora());
        diagnosticoDTO.setDescricao(diagnostico.getDescricao());
        return diagnosticoDTO;
    }

    private Diagnostico convertToEntity(DiagnosticoDTO diagnosticoDTO) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(diagnosticoDTO.getId());
        diagnostico.setPacienteId(diagnosticoDTO.getPacienteId());
        diagnostico.setMedicoId(diagnosticoDTO.getMedicoId());
        diagnostico.setDataHora(diagnosticoDTO.getDataHora());
        diagnostico.setDescricao(diagnosticoDTO.getDescricao());
        return diagnostico;
    }
}
