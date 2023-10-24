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
                    menu.criarReceita();
                    menu.escrever("Receita de %s adicionada com sucesso!", gerenciador.getLastReceita().getTitulo());
                    menu.mostrarReceita(gerenciador.getLastReceita().getId());
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