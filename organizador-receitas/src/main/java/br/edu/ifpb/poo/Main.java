package br.edu.ifpb.poo;

import br.edu.ifpb.poo.gerenciador.Gerenciador;
import br.edu.ifpb.poo.gui.Project;

public class Main {
    public static void main(String[] args) {
        new Gerenciador().conectarArmazenamento();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Project.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Project().setVisible(true));
    }
}