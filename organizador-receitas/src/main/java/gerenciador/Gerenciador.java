package gerenciador;

import armazenamento.Armazenamento;
import classes.Ingredientes;
import classes.Receita;
import enuns.TipoReceita;
import enuns.TipoUnidadeMedida;

import java.util.ArrayList;
import java.util.Arrays;


public class Gerenciador implements IGerenciador {
    @Override
    public void add(String titulo, TipoReceita tipo) {
        Armazenamento.listaReceitas.add(new Receita(titulo, tipo));
        System.out.println(String.join(" ", "A receita", titulo, "foi feita com sucesso."));
    }

    @Override
    public void addIngrediente(int quantidade, TipoUnidadeMedida tipoUnidade, String nome) {
        getLastReceita().addIngredientes(new Ingredientes(quantidade, tipoUnidade, nome));
        System.out.println("O ingrediente " + nome + " foi adicionando");
    }

    @Override
    public void addModoDePreparo(String modoDePreparo) {
        getLastReceita().setModoDePreparo(modoDePreparo);
    }

    @Override
    public void update(int index) {

    }

    @Override
    public void updade(Receita receita, int index) {

    }

    @Override
    public void readReceita() {
        listarReceitasPorFiltro(Armazenamento.listaReceitas);
    }

    @Override
    public void readReceita(TipoReceita... tipos) {
        ArrayList<Receita> receitasFiltradas = (ArrayList<Receita>) Armazenamento.listaReceitas.stream().filter(receita -> Arrays.asList(tipos).contains(receita.getTipo())).toList();
        listarReceitasPorFiltro(receitasFiltradas);
    }

    @Override
    public void readReceita(String titulo) {
        ArrayList<Receita> receitasFiltradas = (ArrayList<Receita>) Armazenamento.listaReceitas.stream().filter(receita -> receita.getTitulo().contains(titulo)).toList();
        listarReceitasPorFiltro(receitasFiltradas);
    }

    @Override
    public Receita getReceita(int index) {
        return Armazenamento.listaReceitas.get(index);
    }

    @Override
    public void delete(int index) {
        System.out.println("A receita " + getReceita(index).getTitulo() + " foi removida.");
        Armazenamento.listaReceitas.remove((index));
    }

    @Override
    public void delete(Receita receita, int index) {
        receita.getListaIngredientes().remove(index);
    }

    private Receita getLastReceita(){
        return getReceita(Armazenamento.listaReceitas.size() - 1);
    }

    private void listarReceitasPorFiltro(ArrayList<Receita> receitas){
        for(int i = 0; i <= receitas.size(); i++ ){
            System.out.printf("%s - %s", i, receitas.get(i));
        }
        // Corrigir a entrada de indexs
    }
}
