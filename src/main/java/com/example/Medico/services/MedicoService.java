package com.example.Medico.services;

import com.example.Medico.dtos.MedicoDTO;
import com.example.Medico.mappers.MedicoMapper;
import com.example.Medico.models.Medico;
import com.example.Medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    //Consultar todos os médicos
    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll().stream()
                .map(MedicoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    //Consultar médicos pelo id
    public Optional<MedicoDTO> findById(Long id) {
        return medicoRepository.findById(id)
                .map(MedicoMapper::convertToDTO);
    }

    //Criar novo médico
    public MedicoDTO save(MedicoDTO medicoDTO) {
        Medico medico = MedicoMapper.convertToEntity(medicoDTO);
        return MedicoMapper.convertToDTO(medicoRepository.save(medico));
    }

    //Deletar o médico
    public void deleteById(Long id) {
        medicoRepository.deleteById(id);
    }


}

