package com.example.Medico.controllers;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.dtos.PacienteDTO;
import com.example.Medico.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public Page<PacienteDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pacienteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        Optional<PacienteDTO> paciente = pacienteService.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PacienteDTO pacienteDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(pacienteService.save(pacienteDTO));
    }

@PatchMapping("/{id}")
public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO, BindingResult result) {
    if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(result.getAllErrors());
    }
    Optional<PacienteDTO> updatedPaciente = pacienteService.update(id, pacienteDTO);
    return updatedPaciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

