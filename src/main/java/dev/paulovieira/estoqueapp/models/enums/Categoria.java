package dev.paulovieira.estoqueapp.models.enums;

public enum Categoria {

    ALIMENTICIO("Alimentício"),
    BEBIDA("Bebida"),
    HIGIENE("Higiene"),
    LIMPEZA("Limpeza"),
    ELETRONICO("Eletrônico"),
    MOVEIS("Móveis"),
    PET("Pet"),
    ROUPA("Roupa"),
    ACESSORIO("Acessório"),
    CALCADO("Calçado");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
