package com.example.Medico.validator;

import com.example.Medico.dtos.ConsultaDTO;
import org.springframework.stereotype.Component;


public abstract class ValidadorConsulta {
    protected ValidadorConsulta proximo;

    public void setProximo(ValidadorConsulta proximo) {
        this.proximo = proximo;
    }

    //Chama os validadores
    public void validar(ConsultaDTO consultaDTO) throws Exception {
        executarValidacao(consultaDTO);
        if (proximo != null) {
            proximo.validar(consultaDTO);
        }
    }

    protected abstract void executarValidacao(ConsultaDTO consultaDTO) throws Exception;
}