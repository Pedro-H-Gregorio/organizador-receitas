package br.edu.ifpb.poo.enuns;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TipoReceita {

    VEGETARIANA("v", "Vegetariana"),
    MASSAS("m", "Massas"),
    DOCES("d", "Doces"),
    CARNES("c", "Carnes"),
    SOBREMESAS("s", "Sobremesas"),
    JANTAR("j", "Jantar"),
    ALMOCO("al", "Almoço"),
    CAFE_DA_MANHA("cm", "Café da Manhã"),
    VEGANA("vg", "Vegana");

    private String key;
    private String descricao;

    TipoReceita(String key, String descricao) {
        this.key = key;
        this.descricao = descricao;
    }

    public String getKey() {
        return key;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String[] getTiposReceitasInString() {
        return Arrays.stream(TipoReceita.values()).map(TipoReceita::getDescricao).collect(Collectors.toList())
                .toArray(new String[0]);
    }
}
