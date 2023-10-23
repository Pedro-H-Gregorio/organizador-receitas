package classes;

import enuns.TipoUnidadeMedida;

public class Ingrediente {

    private float quantidade;
    private TipoUnidadeMedida tipoMedida;
    private String nome;

    public Ingrediente(float quantidade, TipoUnidadeMedida tipoMedida, String nome) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.tipoMedida = tipoMedida;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public TipoUnidadeMedida getTipoMedida() {
        return tipoMedida;
    }

    public String getNome() {
        return nome;
    }

}
