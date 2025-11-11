package com.example.Medico.controllers;

import com.example.Medico.dtos.MedicoDTO;
import com.example.Medico.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<MedicoDTO> medicos = medicoService.findAll();
        if (medicos.isEmpty()) {
            return ResponseEntity.ok("Nenhum médico cadastrado.");
        }
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return medicoService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Médico não encontrado."));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody MedicoDTO medicoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(medicoService.save(medicoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity.ok("Médico removido com sucesso.");
    }
}
