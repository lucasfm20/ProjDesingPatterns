package com.example.Medico.controllers;

import com.example.Medico.models.Ajuda;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajuda")
public class AjudaController {

    @GetMapping
    public Ajuda getAjuda(){

    return Ajuda.getAll();

    }
}
