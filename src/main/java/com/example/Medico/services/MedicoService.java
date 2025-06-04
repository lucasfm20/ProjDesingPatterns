package com.example.Medico.services;

import com.example.Medico.dtos.MedicoDTO;
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

    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MedicoDTO> findById(Long id) {
        return medicoRepository.findById(id)
                .map(this::convertToDTO);
    }

    public MedicoDTO save(MedicoDTO medicoDTO) {
        Medico medico = convertToEntity(medicoDTO);
        return convertToDTO(medicoRepository.save(medico));
    }

    public void deleteById(Long id) {
        medicoRepository.deleteById(id);
    }

    private MedicoDTO convertToDTO(Medico medico) {
        MedicoDTO medicoDTO = new MedicoDTO();
        medicoDTO.setId(medico.getId());
        medicoDTO.setNome(medico.getNome());
        medicoDTO.setEspecialidade(medico.getEspecialidade());
        medicoDTO.setContato(medico.getContato());
        return medicoDTO;
    }

    private Medico convertToEntity(MedicoDTO medicoDTO) {
        Medico medico = new Medico();
        medico.setId(medicoDTO.getId());
        medico.setNome(medicoDTO.getNome());
        medico.setEspecialidade(medicoDTO.getEspecialidade());
        medico.setContato(medicoDTO.getContato());
        return medico;
    }
}

