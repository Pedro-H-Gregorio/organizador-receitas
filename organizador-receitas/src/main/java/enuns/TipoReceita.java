package enuns;

public enum TipoReceita {

    VEGETARIANA("v", "Vegetariano"),
    MASSA("m", "Massas"),
    DOCE("d", "Doces"),
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
}
