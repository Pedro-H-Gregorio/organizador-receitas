package gerenciador;

import armazenamento.Armazenamento;
import classes.Ingrediente;
import classes.Receita;
import enuns.TipoReceita;
import enuns.TipoUnidadeMedida;

import java.util.ArrayList;
import java.util.Arrays;


public class Gerenciador implements IGerenciador {
    @Override
    public void add(String titulo, TipoReceita tipo) {
        Armazenamento.listaReceitas.add(new Receita(Armazenamento.id,titulo, tipo));
        Armazenamento.id++;
        System.out.println(String.join(" ", "A receita", titulo, "foi feita com sucesso."));
    }

    @Override
    public void addIngrediente(int quantidade, TipoUnidadeMedida tipoUnidade, String nome) {
        getLastReceita().addIngrediente(new Ingrediente(quantidade, tipoUnidade, nome));
        System.out.println("O ingrediente " + nome + " foi adicionando");
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
        getReceitaById(idReceita).updateIngrediente(idIngrediente,ingrediente);
    }

    @Override
    public void readReceitas() {
        listarReceitasPorFiltro(Armazenamento.listaReceitas);
    }

    @Override
    public void readReceitas(TipoReceita... tipos) {
        ArrayList<Receita> receitasFiltradas = (ArrayList<Receita>) Armazenamento.listaReceitas.stream().filter(receita -> Arrays.asList(tipos).contains(receita.getTipo())).toList();
        listarReceitasPorFiltro(receitasFiltradas);
    }

    @Override
    public void readReceitas(String titulo) {
        ArrayList<Receita> receitasFiltradas = (ArrayList<Receita>) Armazenamento.listaReceitas.stream().filter(receita -> receita.getTitulo().contains(titulo)).toList();
        listarReceitasPorFiltro(receitasFiltradas);
    }

    @Override
    public void readReceita(int idReceita) {
        Receita receita = getReceitaById(idReceita);
        System.out.println(receita.getTitulo());
        System.out.println(receita.getTipo());
        listarIngredientes(receita.getListaIngredientes());
        System.out.println(receita.getModoDePreparo());
    }

    @Override
    public Receita getReceitaById(int id) {
        return (Receita) Armazenamento.listaReceitas.stream().filter(receita -> receita.getId() == id);
    }

    @Override
    public void delete(int idReceita) {
        Armazenamento.listaReceitas.removeIf(receitas -> receitas.getId() == idReceita);
        System.out.println("A receita " + getReceitaById(idReceita).getTitulo() + " foi removida.");
    }

    @Override
    public void listarIngredientes(ArrayList<Ingrediente> ingredientes){
        for(int i = 0; i<=ingredientes.size(); i++){
            System.out.printf("id: %s | Quantidade: %s%s (%s -> %s) -  %s",
                    i,
                    ingredientes.get(i).getQuantidade()
                    ,ingredientes.get(i).getTipoMedida().getKey()
                    ,ingredientes.get(i).getTipoMedida().getKey()
                    ,ingredientes.get(i).getTipoMedida().getDescricao()
                    ,ingredientes.get(i).getNome());
        }
    }

    private Receita getLastReceita(){
        return Armazenamento.listaReceitas.get(Armazenamento.listaReceitas.size() - 1);
    }

    private void listarReceitasPorFiltro(ArrayList<Receita> receitas){
        for (Receita receita: receitas) {
            System.out.printf("%s - %s", receita.getId(), receita.getTitulo());
        }
    }
}
