package com.example.Medico.controllers;

import com.example.Medico.dtos.TratamentoDTO;
import com.example.Medico.services.TratamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tratamentos")
public class TratamentoController {
    @Autowired
    private TratamentoService tratamentoService;

    @GetMapping
    public List<TratamentoDTO> findAll() {
        return tratamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamentoDTO> findById(@PathVariable Long id) {
        Optional<TratamentoDTO> tratamento = tratamentoService.findById(id);
        return tratamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TratamentoDTO tratamentoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(tratamentoService.save(tratamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tratamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
