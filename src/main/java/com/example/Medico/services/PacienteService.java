package com.example.Medico.services;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.dtos.PacienteDTO;
import com.example.Medico.mappers.PacienteMapper;
import com.example.Medico.models.Paciente;
import com.example.Medico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Page<PacienteDTO> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable)
                .map(PacienteMapper::convertToDTO);
    }

    public Optional<PacienteDTO> findById(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteMapper::convertToDTO);
    }

    public PacienteDTO save(PacienteDTO pacienteDTO) {
        Paciente paciente = PacienteMapper.convertToEntity(pacienteDTO);
        return PacienteMapper.convertToDTO(pacienteRepository.save(paciente));
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }


    public Optional<PacienteDTO> update(Long id, PacienteDTO pacienteDTO) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            if (pacienteDTO.getNome() != null) {
                existingPaciente.setNome(pacienteDTO.getNome());
            }
            if (pacienteDTO.getDataDeNascimento() != null) {
                existingPaciente.setDataDeNascimento(pacienteDTO.getDataDeNascimento());
            }
            if (pacienteDTO.getContato() != null) {
                existingPaciente.setContato(pacienteDTO.getContato());
            }
            Paciente update =pacienteRepository.save(existingPaciente);
            return PacienteMapper.convertToDTO(update);
        });
    }


}
