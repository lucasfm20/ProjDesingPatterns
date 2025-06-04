package com.example.Medico.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do paciente é obrigatorio")
    @Size(min = 2, max = 50, message = "O nome deve ter no minimo 2 caracteres e no máximo 50")
    private String nome;
    @NotNull(message = "A data de nascimento do paciente é obrigatório")
    @Past
    private LocalDate dataDeNascimento;

    @NotNull(message = "O contato do paciente é obrigatório")
    @Size(min = 10, max = 15, message = "O contato deve ter 10 e 15 números")
    private String contato;

    public Paciente(Long id, String nome, LocalDate dataDeNascimento, String contato) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.contato = contato;
    }

    public Paciente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
