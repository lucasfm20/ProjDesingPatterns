package com.example.Medico.services;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.models.Consulta;
import com.example.Medico.repository.ConsultaRepository;
import com.example.Medico.validator.ValidaConflitoHorario;
import com.example.Medico.validator.ValidaPaciente;
import com.example.Medico.validator.ValidadorConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Page<ConsultaDTO> findAll(Pageable pageable) {
        return consultaRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    public Optional<ConsultaDTO> findById(Long id) {
        return consultaRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Autowired
    private ValidaPaciente validaPaciente;

    @Autowired
    private ValidaConflitoHorario validaConflitoHorario;

    public ConsultaDTO save(ConsultaDTO consultaDTO) {
        try {
            validaPaciente.setProximo(validaConflitoHorario);
            validaPaciente.validar(consultaDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erro na validação: " + e.getMessage());
        }

        Consulta consulta = convertToEntity(consultaDTO);
        return convertToDTO(consultaRepository.save(consulta));
    }

    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }

    private ConsultaDTO convertToDTO(Consulta consulta) {
        ConsultaDTO consultaDTO = new ConsultaDTO();
        consultaDTO.setId(consulta.getId());
        consultaDTO.setPacienteId(consulta.getPacienteId());
        consultaDTO.setMedicoId(consulta.getMedicoId());
        consultaDTO.setDataHora(consulta.getDataHora());
        consultaDTO.setDescricao(consulta.getDescricao());
        return consultaDTO;
    }

    private Consulta convertToEntity(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setId(consultaDTO.getId());
        consulta.setPacienteId(consultaDTO.getPacienteId());
        consulta.setMedicoId(consultaDTO.getMedicoId());
        consulta.setDataHora(consultaDTO.getDataHora());
        consulta.setDescricao(consultaDTO.getDescricao());
        return consulta;
    }

}
