package com.example.Medico.controllers;

import com.example.Medico.dtos.TratamentoDTO;
import com.example.Medico.services.TratamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tratamentos")
public class TratamentoController {
    @Autowired
    private TratamentoService tratamentoService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<TratamentoDTO> tratamentos = tratamentoService.findAll();
        if (tratamentos.isEmpty()) {
            return ResponseEntity.ok("Nenhum tratamento cadastrado.");
        }
        return ResponseEntity.ok(tratamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return tratamentoService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Tratamento não encontrado."));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TratamentoDTO tratamentoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(tratamentoService.save(tratamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        tratamentoService.deleteById(id);
        return ResponseEntity.ok("Tratamento removido com sucesso.");
    }
}
