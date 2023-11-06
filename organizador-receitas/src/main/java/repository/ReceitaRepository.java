package repository;

import armazenamento.Armazenamento;
import domain.Receita;

import java.util.ArrayList;

public class ReceitaRepository implements IReceitaRepository {

    private static ReceitaRepository instance;

    public static ReceitaRepository getInstance() {
        if (instance == null) {
            instance = new ReceitaRepository();
        }

        return instance;
    }

    @Override
    public void add(Receita receita) {
        Armazenamento.add(receita);
    }

    @Override
    public void update(int id, Receita receita) {
        remove(id);
        add(receita);
    }

    @Override
    public ArrayList<Receita> getAll() {
        return Armazenamento.listaReceitas;
    }

    @Override
    public Receita getById(int id) {
        return Armazenamento.listaReceitas.stream().filter(receita -> receita.getId() == id).findFirst().get();
    }

    @Override
    public void remove(int id) {
        Armazenamento.remove(getById(id));
    }
}
