package classes;

import enuns.TipoReceita;

import java.io.Serializable;
import java.util.ArrayList;

public class Receita implements Serializable {

    private int id;
    private String titulo;
    private TipoReceita tipo;
    private ArrayList<Ingrediente> listaIngredientes = new ArrayList<>();
    private String modoDePreparo;

    public Receita(int id, String titulo, TipoReceita tipo) {
        this.id = id;
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

    public ArrayList<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void addIngrediente(Ingrediente ingrediente) {
        this.listaIngredientes.add(ingrediente);
    }

    public void removeIngrediente(int idIngrediente) {
        this.listaIngredientes.remove(idIngrediente);
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListaIngredientes(ArrayList<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }
}