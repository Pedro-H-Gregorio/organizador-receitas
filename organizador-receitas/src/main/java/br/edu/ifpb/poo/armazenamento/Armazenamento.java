package br.edu.ifpb.poo.armazenamento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.poo.classes.Receita;

public class Armazenamento {
    protected static final List<Receita> listaReceitas = new ArrayList<>();
    private static int id;

    private Armazenamento() {
    }

    public static void serializacao() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("armazenamento.txt"))) {
            out.writeObject(listaReceitas);
        } catch (IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void desserializacao() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("armazenamento.txt"))) {
            ArrayList<Receita> receitasSalvas = (ArrayList<Receita>) (input.readObject());
            for (Receita receita : receitasSalvas)
                listaReceitas.add(receita);
            setId();
        } catch (IOException erro) {
            System.out.println(String.format("Erro: %s", erro.getMessage()));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static List<Receita> getReceita() {
        return listaReceitas;
    }

    public static int getNewId() {
        return id++;
    }

    private static void setId() {
        int idMaior = 0;
        for (Receita receita : listaReceitas)
            if (receita.getId() >= idMaior)
                idMaior = receita.getId() + 1;
        id = idMaior;
    }
}
