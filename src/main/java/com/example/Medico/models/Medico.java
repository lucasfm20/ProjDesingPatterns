package com.example.Medico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome do medico é obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve ter no minimo 2 caracteres e no máximo 50")
    private String nome;

    @NotNull(message = "A especialidade do medico é obrigatória")
    @Size(min = 2, max = 50, message = "A especialidade deve ter no minimo 2 caracteres e no máximo 50")
    private String especialidade;
    @NotNull(message = "O contato do medico é obrigatório")
    @Size(min = 10, max = 15, message = "O contato deve ter entre 10 e 15 números")
    private String contato;


    public Medico(Long id, String nome, String especialidade, String contato) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.contato = contato;
    }

    public Medico() {
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
