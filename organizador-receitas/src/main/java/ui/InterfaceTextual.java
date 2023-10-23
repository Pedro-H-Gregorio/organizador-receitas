package ui;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Ingrediente;
import classes.Receita;

public class InterfaceTextual {
    public static final Scanner ENTRADA = new Scanner(System.in);
    private ArrayList<String> opcoes = new ArrayList<>();
    private String titulo;

    public InterfaceTextual(String titulo) {
        setTitulo(titulo);
    }

    public InterfaceTextual(String titulo, String... opcoes) {
        setTitulo(titulo);
        for (String opcao : opcoes)
            getOpcoes().add(opcao);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    public void listar(ArrayList<?> lista) {
        System.out.println("==================================================");
        for (int i = 0; i < lista.size(); i++)
            System.out.printf("%s - %s%n", i + 1, lista.get(i));
    }

    public void listar(String... lista) {
        System.out.println("==================================================");
        for (int i = 0; i < lista.length; i++)
            System.out.printf("%s - %s%n", i + 1, lista[i]);
    }

    public void escrever(String mensagem) {
        System.out.println("==================================================");
        System.out.println(mensagem);
    }

    public void escrever(String mensagem, Object... args) {
        System.out.println("==================================================");
        System.out.printf(mensagem + "\n", args);
    }

    public int esperarRespostaInt(String mensagem) {
        int resposta = 0;
        System.out.println("==================================================");
        System.out.print(mensagem);
        do {
            resposta = ENTRADA.nextInt();
            if (resposta == 0)
                escrever("Preencha o campo.");
        } while (resposta == 0);
        return resposta;
    }

    public float esperarRespostaFloat(String mensagem) {
        float resposta = 0;
        System.out.println("==================================================");
        System.out.print(mensagem);
        do {
            resposta = ENTRADA.nextFloat();
            if (resposta == 0)
                escrever("Preencha o campo.");
        } while (resposta == 0);
        return resposta;
    }

    public String esperarRespostaString(String mensagem) {
        String resposta;
        System.out.println("==================================================");
        System.out.print(mensagem);
        ENTRADA.nextLine();
        do {
            resposta = ENTRADA.nextLine();
            if (resposta == "")
                escrever("Preencha o campo.");
        } while (resposta == "");
        return resposta;
    }

    public String esperarRespostaTexto(String mensagem) {
        System.out.println("==================================================");
        System.out.println(mensagem);
        String texto = "", linha = "";
        ENTRADA.nextLine();
        do {
            linha = ENTRADA.nextLine();
            if (!linha.endsWith(":q") || linha.length() > 2)
                texto += "\n" + linha;
        } while (!linha.endsWith(":q") || linha.length() > 2);
        return texto;
    }

    public void esperarResposta() {
        System.out.println("==================================================");
        System.out.print("Pressione o \"Enter\" para continuar...");
        ENTRADA.nextLine();
        ENTRADA.nextLine();
    }

    public int listarOpcoes() {
        int opcaoSelecionada;
        do {
            escrever(titulo);
            listar(opcoes);
            opcaoSelecionada = esperarRespostaInt(String.format("Escolha (1 - %s): ", opcoes.size()));
            if (opcaoSelecionada < 1 || opcaoSelecionada > opcoes.size())
                escrever("Opção Inválida.");
        } while (opcaoSelecionada < 1 || opcaoSelecionada > opcoes.size());
        return opcaoSelecionada;
    }

    public void listarIngredientes(ArrayList<Ingrediente> ingredientes) {
        escrever("Ingredientes Adicionados");
        System.out.println("==================================================");
        for (int i = 0; i < ingredientes.size(); i++)
            System.out.printf("%s - Quantidade: %s%s (%s -> %s) - %s\n", i + 1,
                    ingredientes.get(i).getQuantidade(), ingredientes.get(i).getTipoMedida().getKey(),
                    ingredientes.get(i).getTipoMedida().getKey(), ingredientes.get(i).getTipoMedida().getDescricao(),
                    ingredientes.get(i).getNome());
    }

    public void listarReceitas(ArrayList<Receita> receitas) {
        escrever("Receitas");
        System.out.println("==================================================");
        if (receitas.isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < receitas.size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, receitas.get(i).getTitulo(),
                        receitas.get(i).getTipo().getDescricao());

    }

    public void mostrarReceita(Receita receita) {
        escrever(receita.getTitulo());
        escrever(receita.getTipo().getDescricao());
        listarIngredientes(receita.getListaIngredientes());
        escrever("Modo de Preparo");
        escrever(receita.getModoDePreparo());
    }
}
