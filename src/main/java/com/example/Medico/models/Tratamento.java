package com.example.Medico.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Tratamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ID do diagnóstico é obrigatório")
    private Long diagnosticoId;

    @NotNull(message = "O ID do medicamento é obrigatório")
    private String medicamentoId;

    @NotNull
    @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres")
    private String descricao;

    @NotNull
    @Size(min = 5, max = 255, message = "A duração deve ter entre 5 e 255 caracteres")
    private String duracao;

    public Tratamento(Long id, Long diagnosticoId, String medicamentoId, String descricao, String duracao) {
        this.id = id;
        this.diagnosticoId = diagnosticoId;
        this.medicamentoId = medicamentoId;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public Tratamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiagnosticoId() {
        return diagnosticoId;
    }

    public void setDiagnosticoId(Long diagnosticoId) {
        this.diagnosticoId = diagnosticoId;
    }

    public String getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(String medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
