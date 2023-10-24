import classes.Ingrediente;
import classes.Receita;
import gerenciador.Gerenciador;
import ui.MenuPrincipal;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MenuPrincipal menu = new MenuPrincipal();
        Gerenciador gerenciador = new Gerenciador();
        int opcaoSelecionada;

        gerenciador.conectarArmazenamento();

        do {
            opcaoSelecionada = menu.listarOpcoes();
            switch (opcaoSelecionada) {
                case 1:
                    Receita receita = menu.criarReceita();
                    gerenciador.add(receita.getTitulo(), receita.getTipo());
                    for (Ingrediente ingrediente : receita.getListaIngredientes())
                        gerenciador.addIngrediente(ingrediente);
                    gerenciador.addModoDePreparo(receita.getModoDePreparo());
                    menu.escrever("Receita de %s adicionada com sucesso!", receita.getTitulo());
                    menu.mostrarReceita(receita);
                    break;
                case 2:
                    if (gerenciador.readReceitas().isEmpty())
                        menu.escrever("Nenhuma receita adicionada.");
                    else
                        menu.interagirReceita(menu.buscarReceita());
                    break;
            }
            menu.esperarResposta();
        } while (opcaoSelecionada != menu.getOpcoes().size());
        gerenciador.salvarArmazenamento();
    }
}