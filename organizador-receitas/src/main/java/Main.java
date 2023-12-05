import gerenciador.Gerenciador;
import gui.Project;
import ui.MenuPrincipal;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // MenuPrincipal menu = new MenuPrincipal();
        Gerenciador gerenciador = new Gerenciador();
        // int opcaoSelecionada;
        gerenciador.conectarArmazenamento();

        // do {
        // opcaoSelecionada = menu.listarOpcoes();
        // switch (opcaoSelecionada) {
        // case 1:
        // menu.criarReceita();
        // menu.escrever("Receita de %s adicionada com sucesso!",
        // gerenciador.getLastReceita().getTitulo());
        // menu.mostrarReceita(gerenciador.getLastReceita().getId());
        // break;
        // case 2:
        // if (gerenciador.readReceitas().isEmpty())
        // menu.escrever("Nenhuma receita adicionada.");
        // else
        // menu.interagirReceita(menu.buscarReceita());
        // break;
        // }
        // menu.esperarResposta();
        // } while (opcaoSelecionada != menu.getOpcoes().size());
        // gerenciador.salvarArmazenamento();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Project().setVisible(true);
            }
        });
    }
}