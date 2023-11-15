package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Receita;
import enuns.TipoReceita;
import gerenciador.Gerenciador;

public class InterfaceTextual implements IInterfaceTextual {
    public static final Scanner ENTRADA = new Scanner(System.in);
    private ArrayList<String> opcoes = new ArrayList<>();
    private Gerenciador gerenciador = new Gerenciador();
    private String titulo;

    public InterfaceTextual(String titulo) {
        setTitulo(titulo);
    }

    public InterfaceTextual(String titulo, String... opcoes) {
        setTitulo(titulo);
        for (String opcao : opcoes)
            getOpcoes().add(opcao);
    }

    @Override
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public Gerenciador getGerenciador() {
        return gerenciador;
    }

    @Override
    public ArrayList<String> getOpcoes() {
        return opcoes;
    }

    @Override
    public void listar(ArrayList<?> lista) {
        System.out.println("==================================================");
        for (int i = 0; i < lista.size(); i++)
            System.out.printf("%s - %s%n", i + 1, lista.get(i));
    }

    @Override
    public void listar(String... lista) {
        System.out.println("==================================================");
        for (int i = 0; i < lista.length; i++)
            System.out.printf("%s - %s%n", i + 1, lista[i]);
    }

    @Override
    public void escrever(String mensagem) {
        System.out.println("==================================================");
        System.out.println(mensagem);
    }

    @Override
    public void escrever(String mensagem, Object... args) {
        System.out.println("==================================================");
        System.out.printf(mensagem + "\n", args);
    }

    @Override
    public int esperarRespostaInt(String mensagem) {
        int resposta = 0;

        System.out.println("==================================================");
        System.out.print(mensagem);

        do
            try {
                resposta = ENTRADA.nextInt();
                if (resposta <= 0)
                    throw new InputMismatchException();
            } catch (InputMismatchException e) {
                escrever("Preencha o campo com um valor válido.");
                ENTRADA.nextLine();
                System.out.println("==================================================");
                System.out.print(mensagem);
            }
        while (resposta <= 0);
        return resposta;

    }

    @Override
    public float esperarRespostaFloat(String mensagem) {
        float resposta = 0;

        System.out.println("==================================================");
        System.out.print(mensagem);

        do
            try {
                resposta = ENTRADA.nextFloat();
                if (resposta <= 0)
                    throw new InputMismatchException();
            } catch (InputMismatchException e) {
                escrever("Preencha o campo com um valor válido.");
                ENTRADA.nextLine();
                System.out.println("==================================================");
                System.out.print(mensagem);
            }
        while (resposta <= 0);
        return resposta;

    }

    @Override
    public String esperarRespostaString(String mensagem) {
        String resposta;

        System.out.println("==================================================");
        System.out.print(mensagem);
        ENTRADA.nextLine();

        do {
            resposta = ENTRADA.nextLine().trim();
            if (resposta == "")
                escrever("Preencha o campo.");
        } while (resposta == "");
        return resposta;
    }

    @Override
    public String esperarRespostaTexto(String mensagem) {
        String texto = "", linha = "";

        System.out.println("==================================================");
        System.out.println(mensagem);
        ENTRADA.nextLine();

        do {
            linha = ENTRADA.nextLine();
            if (!linha.endsWith(":q") || linha.length() > 2)
                texto += "\n" + linha;
        } while (!linha.endsWith(":q") || linha.length() > 2);
        return texto;
    }

    @Override
    public void esperarResposta() {
        System.out.println("==================================================");
        System.out.print("Pressione o \"Enter\" para continuar...");
        ENTRADA.nextLine();
        ENTRADA.nextLine();
    }

    @Override
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

    @Override
    public void listarIngredientes(int receitaId) {
        Receita receita = gerenciador.getReceitaById(receitaId);
        escrever("Ingredientes Adicionados");
        System.out.println("==================================================");
        for (int i = 0; i < receita.getListaIngredientes().size(); i++)
            System.out.printf("%s - Quantidade: %s%s (%s -> %s) - %s\n", i + 1,
                    receita.getListaIngredientes().get(i).getQuantidade(),
                    receita.getListaIngredientes().get(i).getTipoMedida().getKey(),
                    receita.getListaIngredientes().get(i).getTipoMedida().getKey(),
                    receita.getListaIngredientes().get(i).getTipoMedida().getDescricao(),
                    receita.getListaIngredientes().get(i).getNome());
    }

    @Override
    public void listarReceitas() {
        escrever("Receitas");
        System.out.println("==================================================");
        if (gerenciador.readReceitas().isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < gerenciador.readReceitas().size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, gerenciador.readReceitas().get(i).getTitulo(),
                        gerenciador.readReceitas().get(i).getTipo().getDescricao());
    }

    @Override
    public void listarReceitas(TipoReceita tipo) {
        escrever("Receitas");
        System.out.println("==================================================");
        if (gerenciador.readReceitas(tipo).isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < gerenciador.readReceitas(tipo).size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, gerenciador.readReceitas(tipo).get(i).getTitulo(),
                        gerenciador.readReceitas(tipo).get(i).getTipo().getDescricao());
    }

    @Override
    public void listarReceitas(ArrayList<String> ingredientes) {
        escrever("Receitas");
        System.out.println("==================================================");
        if (gerenciador.readReceitas(ingredientes).isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < gerenciador.readReceitas(ingredientes).size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, gerenciador.readReceitas(ingredientes).get(i).getTitulo(),
                        gerenciador.readReceitas(ingredientes).get(i).getTipo().getDescricao());
    }

    @Override
    public void listarReceitas(String titulo) {
        escrever("Receitas");
        System.out.println("==================================================");
        if (gerenciador.readReceitas(titulo).isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < gerenciador.readReceitas(titulo).size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, gerenciador.readReceitas(titulo).get(i).getTitulo(),
                        gerenciador.readReceitas(titulo).get(i).getTipo().getDescricao());

    }

    @Override
    public void mostrarReceita(int receitaId) {
        Receita receita = gerenciador.getReceitaById(receitaId);
        escrever(receita.getTitulo());
        escrever(receita.getTipo().getDescricao());
        listarIngredientes(receitaId);
        escrever("Modo de Preparo");
        escrever(receita.getModoDePreparo());
    }
}
