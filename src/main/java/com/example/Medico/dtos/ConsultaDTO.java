package com.example.Medico.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ConsultaDTO {
    private Long id;

    //@NotNull(message = "O id do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "O id do médico é obrigatório")
    private Long medicoId;
    @NotNull(message = "A data da consulta é obrigatória")
    @FutureOrPresent
    private LocalDateTime dataHora;
    @NotNull(message = "A descrição da consulta é obrigatoria")
    @Size(min = 10, max = 500, message = "A descrição da consulta deve ter entre 10 e 500 caracteres")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
