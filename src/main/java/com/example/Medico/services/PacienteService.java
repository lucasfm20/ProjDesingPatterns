package com.example.Medico.services;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.dtos.PacienteDTO;
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
                .map(this::convertToDTO);
    }

    public Optional<PacienteDTO> findById(Long id) {
        return pacienteRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PacienteDTO save(PacienteDTO pacienteDTO) {
        Paciente paciente = convertToEntity(pacienteDTO);
        return convertToDTO(pacienteRepository.save(paciente));
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
            return convertToDTO(update);
        });
    }

    private PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setDataDeNascimento(paciente.getDataDeNascimento());
        pacienteDTO.setContato(paciente.getContato());
        return pacienteDTO;
    }

    private Paciente convertToEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.getId());
        paciente.setNome(pacienteDTO.getNome());
        paciente.setDataDeNascimento(pacienteDTO.getDataDeNascimento());
        paciente.setContato(pacienteDTO.getContato());
        return paciente;
    }
}
