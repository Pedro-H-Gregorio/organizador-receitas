package br.edu.ifpb.poo.gerenciador;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import br.edu.ifpb.poo.armazenamento.Armazenamento;
import br.edu.ifpb.poo.classes.Ingrediente;
import br.edu.ifpb.poo.classes.Receita;
import br.edu.ifpb.poo.enuns.TipoReceita;
import br.edu.ifpb.poo.enuns.TipoUnidadeMedida;

public class Gerenciador implements IGerenciador {
    @Override
    public void add(String titulo, TipoReceita tipo) {
        Armazenamento.getReceita().add(new Receita(Armazenamento.getNewId(), titulo, tipo));
    }

    @Override
    public void addIngrediente(int receitaId, String nome, TipoUnidadeMedida unidadeMedida, String quantidade) {
        getReceitaById(receitaId).addIngrediente(new Ingrediente(quantidade, unidadeMedida, nome));
    }

    @Override
    public void addIngrediente(String nome, TipoUnidadeMedida unidadeMedida, String quantidade) {
        getLastReceita().addIngrediente(new Ingrediente(quantidade, unidadeMedida, nome));
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
    public ArrayList<Receita> readReceitas() {
        return (ArrayList<Receita>) Armazenamento.getReceita();
    }

    @Override
    public ArrayList<Receita> readReceitas(TipoReceita... tipos) {
        return (ArrayList<Receita>) (Armazenamento.getReceita().stream()
                .filter(receita -> Arrays.asList(tipos).contains(receita.getTipo()))).collect(Collectors.toList());
    }

    @Override
    public ArrayList<Receita> readReceitas(ArrayList<String> ingredientes) {
        return (ArrayList<Receita>) (Armazenamento.getReceita().stream()
                .filter(receita -> {
                    boolean resultado = true;
                    for (String nomeIngrediente : ingredientes)
                        resultado &= verificarContemIngrediente(receita.getId(), nomeIngrediente);
                    return resultado;
                }))
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<Receita> readReceitas(String titulo) {
        return (ArrayList<Receita>) (Armazenamento.getReceita().stream()
                .filter(receita -> verificarContemString(receita.getTitulo(), titulo))
                .collect(Collectors.toList()));
    }

    @Override
    public Receita getReceitaById(int id) {
        for (Receita receita : Armazenamento.getReceita())
            if (receita.getId() == id)
                return receita;
        return null;
    }

    @Override
    public String normalizarString(String str) {
        return Normalizer
                .normalize(String.valueOf(str).toLowerCase().trim(),
                        Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    @Override
    public boolean verificarContemString(String string, String subString) {
        return normalizarString(string).contains(normalizarString(subString));
    }

    @Override
    public boolean verificarContemIngrediente(int receitaId, String nomeIngrediente) {
        for (Ingrediente ingrediente : getReceitaById(receitaId).getListaIngredientes())
            if (verificarContemString(ingrediente.getNome(), nomeIngrediente))
                return true;
        return false;
    }

    @Override
    public void delete(int idReceita) {
        Armazenamento.getReceita().removeIf(receitas -> receitas.getId() == idReceita);
    }

    @Override
    public void conectarArmazenamento() {
        Armazenamento.desserializacao();
    }

    @Override
    public void salvarArmazenamento() {
        Armazenamento.serializacao();
    }

    @Override
    public Receita getLastReceita() {
        return Armazenamento.getReceita().get(Armazenamento.getReceita().size() - 1);
    }
}
