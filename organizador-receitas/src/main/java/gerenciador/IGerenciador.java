package gerenciador;

import classes.Ingrediente;
import classes.Receita;
import enuns.TipoReceita;
import enuns.TipoUnidadeMedida;

import java.util.ArrayList;

public interface IGerenciador {
    void add(String titulo, TipoReceita tipo);
    void addIngrediente(int quantidade, TipoUnidadeMedida tipoUnidade, String nome);
    void removeIngrediente(int idReceita, int idIngrediente);
    void addModoDePreparo(String modoDePreparo);
    void updateTitulo(int idReceita,String titulo);
    void updateModoDePreparo(int idReceita, String modoDePreparo);
    void updateTipo(int idReceita, TipoReceita tipo);
    void updadeIngrediente(int idReceita, int idIngrediente, Ingrediente ingrediente);
    void readReceitas();
    void readReceitas(TipoReceita... tipos);
    void readReceitas(String titulo);
    void readReceita(int idReceita);
    Receita getReceitaById(int idReceita);
    void delete(int idReceita);
    void listarIngredientes(ArrayList<Ingrediente> ingredientes);
}
