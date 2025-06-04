package com.example.Medico.controllers;

import com.example.Medico.dtos.MedicoDTO;
import com.example.Medico.models.Medico;
import com.example.Medico.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<MedicoDTO> findAll() {
        return medicoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        Optional<MedicoDTO> medico = medicoService.findById(id);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MedicoDTO medicoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(medicoService.save(medicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
