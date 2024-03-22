package com.netlify.lggdev.crudsistemamedico.enums;

public enum Especialidade {
    ORTOPEDIA("Ortopedia"),
    CARDIOLOGIA("Cardiologia"),
    GINECOLOGIA("Ginecologia"),
    DERMATOLOGIA("Dermatologia");

    private String nomeLegivel;
    Especialidade(String nomeLegivel) {
        this.nomeLegivel = nomeLegivel;
    }

    public String getNomeLegivel() {
        return this.nomeLegivel;
    }
}
