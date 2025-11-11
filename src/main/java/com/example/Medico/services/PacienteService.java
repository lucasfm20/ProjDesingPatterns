package com.example.Medico.services;

import com.example.Medico.dtos.PacienteDTO;
import com.example.Medico.mappers.PacienteMapper;
import com.example.Medico.models.Paciente;
import com.example.Medico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    //Consultar todos os pacientes
    public Page<PacienteDTO> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable)
                .map(PacienteMapper::convertToDTO);
    }

    //Consultar apenas o paciente pelo id
    public Optional<PacienteDTO> findById(Long id) {
        return pacienteRepository.findById(id)
                .map(PacienteMapper::convertToDTO);
    }

    //Criar novo paciente
    public PacienteDTO save(PacienteDTO pacienteDTO) {
        Paciente paciente = PacienteMapper.convertToEntity(pacienteDTO);
        return PacienteMapper.convertToDTO(pacienteRepository.save(paciente));
    }

    //Deletar paciente
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    //Atualizar o registro de um paciente
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
