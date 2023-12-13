package br.edu.ifpb.poo.classes;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifpb.poo.enuns.TipoReceita;

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

    public List<Ingrediente> getListaIngredientes() {
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

    public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
        this.listaIngredientes = (ArrayList<Ingrediente>) listaIngredientes;
    }
}