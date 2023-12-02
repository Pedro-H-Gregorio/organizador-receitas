package ui;

import java.util.ArrayList;

import enuns.TipoReceita;
import gerenciador.Gerenciador;

public interface IInterfaceTextual {
    public void setTitulo(String titulo);

    public String getTitulo();

    public Gerenciador getGerenciador();

    public ArrayList<String> getOpcoes();

    public void listar(ArrayList<?> lista);

    public void listar(String... lista);

    public void escrever(String mensagem);

    public void escrever(String mensagem, Object... args);

    public int esperarRespostaInt(String mensagem);

    public String esperarRespostaString(String mensagem);

    public String esperarRespostaTexto(String mensagem);

    public void esperarResposta();

    public int listarOpcoes();

    public void listarIngredientes(int receitaId);

    public void listarReceitas();

    public void listarReceitas(TipoReceita tipo);

    public void listarReceitas(ArrayList<String> ingredientes);

    public void listarReceitas(String titulo);

    public void mostrarReceita(int receitaId);
}
