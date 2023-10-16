package classes;

import enuns.TipoReceita;

import java.util.LinkedList;

public class Receita {

    private String Titulo;
    private TipoReceita tipo;
    private LinkedList<Ingredientes> listaIngredientes = new LinkedList<>();
    private String modoDePreparo;

    public Receita(String titulo) {
        Titulo = titulo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public TipoReceita getTipo() {
        return tipo;
    }

    public void setTipo(TipoReceita tipo) {
        this.tipo = tipo;
    }

    public LinkedList<Ingredientes> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(Ingredientes ingrediente) {
        this.listaIngredientes.add(ingrediente);
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }
}