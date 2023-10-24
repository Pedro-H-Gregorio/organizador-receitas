package gerenciador;

import classes.Ingrediente;
import classes.Receita;
import enuns.TipoReceita;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IGerenciador {

    void add(String titulo, TipoReceita tipo);
    void addIngrediente(Ingrediente ingrediente);
    void removeIngrediente(int idReceita, int idIngrediente);
    void addModoDePreparo(String modoDePreparo);
    void updateTitulo(int idReceita, String titulo);
    void updateModoDePreparo(int idReceita, String modoDePreparo);
    void updateTipo(int idReceita, TipoReceita tipo);
    void updadeIngrediente(int idReceita, int idIngrediente, Ingrediente ingrediente);
    ArrayList<Receita> readReceitas();
    ArrayList<Receita> readReceitas(TipoReceita... tipos);
    ArrayList<Receita> readReceitas(String titulo);
    Receita getReceitaById(int idReceita);
    Receita getLastReceita();
    void delete(int idReceita);
    void conectarArmazenamento();
    void salvarArmazenamento() throws FileNotFoundException;
}
