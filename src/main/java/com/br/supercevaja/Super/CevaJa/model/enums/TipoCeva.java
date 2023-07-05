package com.br.supercevaja.Super.CevaJa.model.enums;

public enum TipoCeva {

    PILSEN("PILSEN"),
    BOCK("BOCK"),
    IPA("IPA"),
    APA("APA"),
    WEISS("WEISS"),
    STOUT("STOUT");

    private String descricao;

    TipoCeva(String descricao) {
        this.descricao = descricao;
    }
    public TipoCeva getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
