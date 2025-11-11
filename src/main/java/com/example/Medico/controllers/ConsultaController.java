package com.example.Medico.controllers;

import com.example.Medico.dtos.ConsultaDTO;
import com.example.Medico.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConsultaDTO> consultas = consultaService.findAll(pageable);
        if (consultas.isEmpty()) {
            return ResponseEntity.ok("Nenhuma consulta cadastrada.");
        }
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ConsultaDTO consultaDTO = consultaService.findById(id);
        return ResponseEntity.ok(consultaDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ConsultaDTO consultaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            ConsultaDTO savedConsulta = consultaService.save(consultaDTO);
            return ResponseEntity.ok(savedConsulta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        consultaService.deleteById(id);
        return ResponseEntity.ok("Consulta removida com sucesso.");
    }
}