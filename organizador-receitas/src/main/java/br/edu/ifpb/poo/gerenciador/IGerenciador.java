package br.edu.ifpb.poo.gerenciador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import br.edu.ifpb.poo.classes.Receita;
import br.edu.ifpb.poo.enuns.TipoReceita;
import br.edu.ifpb.poo.enuns.TipoUnidadeMedida;

public interface IGerenciador {

    void add(String titulo, TipoReceita tipo);

    void addIngrediente(String nome, TipoUnidadeMedida unidadeMedida, String quantidade);

    void addIngrediente(int receitaId, String nome, TipoUnidadeMedida unidadeMedida, String quantidade);

    void removeIngrediente(int idReceita, int idIngrediente);

    void addModoDePreparo(String modoDePreparo);

    void updateTitulo(int idReceita, String titulo);

    void updateModoDePreparo(int idReceita, String modoDePreparo);

    void updateTipo(int idReceita, TipoReceita tipo);

    ArrayList<Receita> readReceitas();

    ArrayList<Receita> readReceitas(TipoReceita... tipos);

    ArrayList<Receita> readReceitas(ArrayList<String> ingredientes);

    ArrayList<Receita> readReceitas(String titulo);

    Receita getReceitaById(int idReceita);

    Receita getLastReceita();

    String normalizarString(String str);

    boolean verificarContemString(String string, String subString);

    boolean verificarContemIngrediente(int receitaId, String nomeIngrediente);

    void delete(int idReceita);

    void conectarArmazenamento();

    void salvarArmazenamento() throws FileNotFoundException;
}
