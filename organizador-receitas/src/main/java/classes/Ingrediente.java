package classes;

import enuns.TipoUnidadeMedida;

public class Ingrediente {

    private float quantidade;
    private TipoUnidadeMedida tipoMedida;
    private String nome;

    public Ingrediente(int quantidade, TipoUnidadeMedida tipoMedida, String nome) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.tipoMedida = tipoMedida;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
