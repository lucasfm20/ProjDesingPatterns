package com.example.Medico.controllers;

import com.example.Medico.dtos.DiagnosticoDTO;
import com.example.Medico.services.DiagnosticoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<DiagnosticoDTO> diagnosticos = diagnosticoService.findAll();
        if (diagnosticos.isEmpty()) {
            return ResponseEntity.ok("Nenhum diagnóstico cadastrado.");
        }
        return ResponseEntity.ok(diagnosticos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return diagnosticoService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Diagnóstico não encontrado."));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody DiagnosticoDTO diagnosticoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(diagnosticoService.save(diagnosticoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        diagnosticoService.deleteById(id);
        return ResponseEntity.ok("Diagnóstico removido com sucesso.");
    }
}
