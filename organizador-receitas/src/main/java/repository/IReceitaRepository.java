package repository;

import domain.Receita;

import java.util.ArrayList;

public interface IReceitaRepository {

    void add(Receita receita);

    void update(int id ,Receita receita);

    ArrayList<Receita> getAll();

    Receita getById(int id);

    void remove(int id);
}
