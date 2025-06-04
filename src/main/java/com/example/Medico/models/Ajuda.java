package com.example.Medico.models;

import java.util.ArrayList;

public class Ajuda {
    private ArrayList<String> estudantes;
    private String nome;
    private String tema;


    public Ajuda(ArrayList<String> estudantes, String nome, String tema) {
        this.estudantes = estudantes;
        this.nome = nome;
        this.tema = tema;
    }

    public Ajuda() {
    }

    public ArrayList<String> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(ArrayList<String> estudantes) {
        this.estudantes = estudantes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public static Ajuda getAll(){

        ArrayList<String> estudantes = new ArrayList<>();
        estudantes.add("Lucas Fortunato Martins");
        estudantes.add("João Vitor Rodrigues Rocha");


        return new Ajuda(estudantes,"Mapeamento de consultas médicas","Saúde e Bem-estar"
        );
    }
}
