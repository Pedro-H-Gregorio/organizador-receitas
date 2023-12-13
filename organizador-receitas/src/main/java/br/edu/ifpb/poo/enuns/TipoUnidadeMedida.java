package br.edu.ifpb.poo.enuns;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TipoUnidadeMedida {
    COLHER_DE_SOPA("cs", "Colher de sopa"),
    COLHER_DE_CHA("cc", "Colher de chá"),
    GRAMAS("g", "Gramas"),
    UNIDADE("u", "Unidade"),
    XICARA("x", "Xicara");

    private String key;
    private String descricao;

    TipoUnidadeMedida(String key, String descricao) {
        this.key = key;
        this.descricao = descricao;
    }

    public String getKey() {
        return key;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String[] getTiposUnidadeMedidaInString() {
        return Arrays.stream(TipoUnidadeMedida.values()).map(TipoUnidadeMedida::getDescricao)
                .collect(Collectors.toList()).toArray(new String[0]);
    }
}
