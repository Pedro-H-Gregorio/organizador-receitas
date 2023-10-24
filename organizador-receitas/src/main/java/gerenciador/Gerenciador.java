package gerenciador;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import armazenamento.Armazenamento;
import classes.Ingrediente;
import classes.Receita;
import enuns.TipoReceita;
import enuns.TipoUnidadeMedida;

public class Gerenciador implements IGerenciador {
    @Override
    public void add(String titulo, TipoReceita tipo) {
        Armazenamento.listaReceitas.add(new Receita(Armazenamento.id, titulo, tipo));
        Armazenamento.id++;
    }

    @Override
    public void addIngrediente(String nome, TipoUnidadeMedida unidadeMedida, float quantidade) {
        getLastReceita().addIngrediente(new Ingrediente(quantidade, unidadeMedida, nome));
    }

    @Override
    public void addIngrediente(int receitaId, String nome, TipoUnidadeMedida unidadeMedida, float quantidade) {
        getReceitaById(receitaId).addIngrediente(new Ingrediente(quantidade, unidadeMedida, nome));
    }

    @Override
    public void removeIngrediente(int idReceita, int idIngrediente) {
        Receita receita = getReceitaById(idReceita);
        receita.removeIngrediente(idIngrediente);
    }

    @Override
    public void addModoDePreparo(String modoDePreparo) {
        getLastReceita().setModoDePreparo(modoDePreparo);
    }

    @Override
    public void updateTitulo(int idReceita, String titulo) {
        getReceitaById(idReceita).setTitulo(titulo);
    }

    @Override
    public void updateModoDePreparo(int idReceita, String modoDePreparo) {
        getReceitaById(idReceita).setModoDePreparo(modoDePreparo);
    }

    @Override
    public void updateTipo(int idReceita, TipoReceita tipo) {
        getReceitaById(idReceita).setTipo(tipo);
    }

    @Override
    public void updadeIngrediente(int idReceita, int idIngrediente, Ingrediente ingrediente) {
        getReceitaById(idReceita).updateIngrediente(idIngrediente, ingrediente);
    }

    @Override
    public ArrayList<Receita> readReceitas() {
        return Armazenamento.listaReceitas;
    }

    @Override
    public ArrayList<Receita> readReceitas(TipoReceita... tipos) {
        ArrayList<Receita> receitasFiltradas = (ArrayList<Receita>) (Armazenamento.listaReceitas.stream()
                .filter(receita -> Arrays.asList(tipos).contains(receita.getTipo()))).collect(Collectors.toList());
        return receitasFiltradas;
    }

    @Override
    public ArrayList<Receita> readReceitas(String titulo) {
        ArrayList<Receita> receitasFiltradas = (ArrayList<Receita>) (Armazenamento.listaReceitas.stream()
                .filter(receita -> receita.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList()));
        return receitasFiltradas;
    }

    @Override
    public Receita getReceitaById(int id) {
        for (Receita receita : Armazenamento.listaReceitas)
            if (receita.getId() == id)
                return receita;
        return null;
    }

    @Override
    public void delete(int idReceita) {
        Armazenamento.listaReceitas.removeIf(receitas -> receitas.getId() == idReceita);
    }

    @Override
    public void conectarArmazenamento() {
        Armazenamento.desserializacao();
    }

    @Override
    public void salvarArmazenamento() throws FileNotFoundException {
        Armazenamento.serializacao();
    }

    @Override
    public Receita getLastReceita() {
        return Armazenamento.listaReceitas.get(Armazenamento.listaReceitas.size() - 1);
    }
}
