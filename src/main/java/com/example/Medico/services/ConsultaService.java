package com.example.Medico.services;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.mappers.ConsultaMapper;
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
                .map(ConsultaMapper::convertToDTO);
    }

    public Optional<ConsultaDTO> findById(Long id) {
        return consultaRepository.findById(id)
                .map(ConsultaMapper::convertToDTO);
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

        Consulta consulta = ConsultaMapper.convertToEntity(consultaDTO);
        return ConsultaMapper.convertToDTO(consultaRepository.save(consulta));
    }

    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }



}
