package armazenamento;

import domain.Receita;

import java.io.*;
import java.util.ArrayList;

public class Armazenamento {
    public static ArrayList<Receita> listaReceitas = new ArrayList<>();
    public static int id;

    public static void serializacao() throws FileNotFoundException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("armazenamento.txt"))) {
            out.writeObject(listaReceitas);
        } catch (IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void desserializacao() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("armazenamento.txt"))) {
            listaReceitas = (ArrayList<Receita>) input.readObject();
            setId();
        } catch (IOException erro) {
            System.out.printf("Erro: %s\n", erro.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setId() {
        int idMaior = 0;
        for (Receita receita : listaReceitas) {
            if (receita.getId() >= idMaior) {
                idMaior = receita.getId() + 1;
            }
        }
        id = idMaior;
    }

    public static void add(Receita receita){
        receita.setId(id);
        listaReceitas.add(receita);
        id++;
    };

    public static void remove(Receita receita){
        listaReceitas.remove(receita);
    };

}
