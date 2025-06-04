package com.example.Medico.controllers;

import com.example.Medico.dtos.DiagnosticoDTO;
import com.example.Medico.services.DiagnosticoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public List<DiagnosticoDTO> findAll() {
        return diagnosticoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoDTO> findById(@PathVariable Long id) {
        Optional<DiagnosticoDTO> diagnostico = diagnosticoService.findById(id);
        return diagnostico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody DiagnosticoDTO diagnosticoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(diagnosticoService.save(diagnosticoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        diagnosticoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
