package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import enuns.TipoReceita;
import gerenciador.Gerenciador;

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
            try {
                resposta = ENTRADA.nextInt();
                if (resposta == 0)
                    escrever("Preencha o campo.");
            } catch (InputMismatchException e) {
                escrever("Tente Novamente");
                ENTRADA.nextLine();
                System.out.println("==================================================");
                System.out.print(mensagem);
            }
        } while (resposta == 0);
        return resposta;
    }

    public float esperarRespostaFloat(String mensagem) {
        float resposta = 0;
        System.out.println("==================================================");
        System.out.print(mensagem);
        do {
            try {
                resposta = ENTRADA.nextFloat();
                if (resposta == 0)
                    escrever("Preencha o campo.");
            } catch (InputMismatchException e) {
                escrever("Tente Novamente");
                ENTRADA.nextLine();
                System.out.println("==================================================");
                System.out.print(mensagem);
            }
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

    public void listarIngredientes(int receitaId) {
        Gerenciador gerenciador = new Gerenciador();
        escrever("Ingredientes Adicionados");
        System.out.println("==================================================");
        for (int i = 0; i < gerenciador.getReceitaById(receitaId).getListaIngredientes().size(); i++)
            System.out.printf("%s - Quantidade: %s%s (%s -> %s) - %s\n", i + 1,
                    gerenciador.getReceitaById(receitaId).getListaIngredientes().get(i).getQuantidade(),
                    gerenciador.getReceitaById(receitaId).getListaIngredientes().get(i).getTipoMedida().getKey(),
                    gerenciador.getReceitaById(receitaId).getListaIngredientes().get(i).getTipoMedida().getKey(),
                    gerenciador.getReceitaById(receitaId).getListaIngredientes().get(i).getTipoMedida().getDescricao(),
                    gerenciador.getReceitaById(receitaId).getListaIngredientes().get(i).getNome());
    }

    public void listarReceitas() {
        Gerenciador gerenciador = new Gerenciador();
        escrever("Receitas");
        System.out.println("==================================================");
        if (gerenciador.readReceitas().isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < gerenciador.readReceitas().size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, gerenciador.readReceitas().get(i).getTitulo(),
                        gerenciador.readReceitas().get(i).getTipo().getDescricao());
    }

    public void listarReceitas(TipoReceita tipo) {
        Gerenciador gerenciador = new Gerenciador();
        escrever("Receitas");
        System.out.println("==================================================");
        if (gerenciador.readReceitas(tipo).isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < gerenciador.readReceitas(tipo).size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, gerenciador.readReceitas(tipo).get(i).getTitulo(),
                        gerenciador.readReceitas(tipo).get(i).getTipo().getDescricao());
    }

    public void listarReceitas(String titulo) {
        Gerenciador gerenciador = new Gerenciador();
        escrever("Receitas");
        System.out.println("==================================================");
        if (gerenciador.readReceitas(titulo).isEmpty())
            System.out.println("Lista de receitas vazia.");
        else
            for (int i = 0; i < gerenciador.readReceitas(titulo).size(); i++)
                System.out.printf("%s - %-20s | %s\n", i + 1, gerenciador.readReceitas(titulo).get(i).getTitulo(),
                        gerenciador.readReceitas(titulo).get(i).getTipo().getDescricao());

    }

    public void mostrarReceita(int receitaId) {
        Gerenciador gerenciador = new Gerenciador();
        escrever(gerenciador.getReceitaById(receitaId).getTitulo());
        escrever(gerenciador.getReceitaById(receitaId).getTipo().getDescricao());
        listarIngredientes(receitaId);
        escrever("Modo de Preparo");
        escrever(gerenciador.getReceitaById(receitaId).getModoDePreparo());
    }
}
