package enuns;

import java.util.Arrays;

public enum TipoUnidadeMedida {
    COLHER_SOPA("cs", "Colher de sopa"),
    COLHER_CHA("cc", "Colher de chá"),
    GRAMAS("g", "Gramas"),
    UNIDADE("u", "Unidade"),
    XICARA("x","Xicara");

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
        return Arrays.stream(TipoUnidadeMedida.values()).map(TipoUnidadeMedida::getDescricao).toList().toArray(new String[0]);
    }
}
