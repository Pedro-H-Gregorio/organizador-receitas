package classes;

import enuns.TipoUnidadeMedida;

import java.io.Serializable;

public class Ingrediente implements Serializable {

    private String quantidade;
    private TipoUnidadeMedida tipoMedida;
    private String nome;

    public Ingrediente(String quantidade, TipoUnidadeMedida tipoMedida, String nome) {
        this.quantidade = quantidade;
        this.nome = nome;
        this.tipoMedida = tipoMedida;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public TipoUnidadeMedida getTipoMedida() {
        return tipoMedida;
    }

    public String getNome() {
        return nome;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipoMedida(TipoUnidadeMedida tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
