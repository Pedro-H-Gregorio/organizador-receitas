package classes;

import enuns.TipoReceita;

import java.util.ArrayList;

public class Receita {

    private String titulo;
    private TipoReceita tipo;
    private ArrayList<Ingredientes> listaIngredientes = new ArrayList<>();
    private String modoDePreparo;

    public Receita(String titulo, TipoReceita tipo) {
        this.titulo = titulo;
        this.tipo = tipo;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoReceita getTipo() {
        return tipo;
    }

    public void setTipo(TipoReceita tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Ingredientes> getListaIngredientes() {
        return listaIngredientes;
    }

    public void addIngredientes(Ingredientes ingrediente) {
        this.listaIngredientes.add(ingrediente);
    }
    public void removeListaIngredientes(int index){ this.listaIngredientes.remove(index);}

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }
}