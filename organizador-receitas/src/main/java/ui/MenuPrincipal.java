package ui;

import java.util.ArrayList;

import classes.Ingrediente;
import classes.Receita;
import enuns.TipoReceita;
import enuns.TipoUnidadeMedida;
import gerenciador.Gerenciador;

public class MenuPrincipal extends InterfaceTextual {
    public MenuPrincipal() {
        super("Menu Principal");
        getOpcoes().add("Criar receita");
        getOpcoes().add("Buscar receita");
        getOpcoes().add("Sair");
    }

    public TipoUnidadeMedida escolherUnidadeMedida() {
        return TipoUnidadeMedida.values()[new InterfaceTextual("Tipo da Unidade de Medida", TipoUnidadeMedida.getTiposUnidadeMedidaInString()).listarOpcoes() - 1];
    }

    public TipoReceita escolherTipoReceita() {
        return TipoReceita.values()[new InterfaceTextual("Tipo da Receita", TipoReceita.getTiposReceitasInString())
                .listarOpcoes() - 1];
    }

    public Ingrediente criarIngrediente() {
        String nome = esperarRespostaString("Digite o nome do ingrediente: ");
        TipoUnidadeMedida tipo = escolherUnidadeMedida();
        float quantidade = esperarRespostaFloat(String.format("Digite a quantidade de %s: ", nome));
        return new Ingrediente(quantidade, tipo, nome);
    }

    public ArrayList<Ingrediente> criarListaDeIngredientes() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        InterfaceTextual criadorIngredientes = new InterfaceTextual("Ingredientes", "Novo Ingrediente", "Sair");
        int opcaoSelecionada;

        do {
            opcaoSelecionada = criadorIngredientes.listarOpcoes();
            if (opcaoSelecionada == 1)
                ingredientes.add(criarIngrediente());
            else if (ingredientes.isEmpty())
                escrever("Adicione pelo menos um ingrediente.");
        } while (opcaoSelecionada != 2 || ingredientes.isEmpty());
        return ingredientes;
    }

    public Receita criarReceita() {
        String titulo = esperarRespostaString("Digite o título da receita: ");
        TipoReceita tipo = escolherTipoReceita();
        ArrayList<Ingrediente> ingredientes = criarListaDeIngredientes();
        String modoDePreparo = esperarRespostaTexto(
                "Escreva o modo de preparo, quando finalizar\npule linha e digite \":q\":\n");

        Receita receita = new Receita(titulo, tipo); // usar o gerenciador
        for (Ingrediente ingrediente : ingredientes)
            receita.addIngrediente(ingrediente); // usar gerenciador
        receita.setModoDePreparo(modoDePreparo); // usar gerenciador
        return receita;
    }

    public Receita buscarReceita() {
        ArrayList<Receita> resultadoBusca;
        Receita receita = null;
        Gerenciador gerenciador = new Gerenciador();
        InterfaceTextual buscadorReceita = new InterfaceTextual("Buscar Receita", "Buscar receita pelo nome",
                "Buscar receita pelo tipo");
        do {
            switch (buscadorReceita.listarOpcoes()) {
                case 1:
                    String nome = esperarRespostaString("Digite o nome da receita: ");
                    resultadoBusca = gerenciador.readReceitas(nome);
                    if (resultadoBusca.isEmpty())
                        escrever("Não existe nenhuma receita com este nome.");
                    else {
                        listarReceitas(resultadoBusca);
                        receita = resultadoBusca.get(esperarRespostaInt("Escolha: ") - 1);
                    }
                    break;
                case 2:
                    TipoReceita tipo = escolherTipoReceita();
                    resultadoBusca = gerenciador.readReceitas(tipo);
                    if (resultadoBusca.isEmpty())
                        escrever("Não existe nenhuma receita com deste tipo ainda.");
                    else {
                        listarReceitas(resultadoBusca);
                        receita = resultadoBusca.get(esperarRespostaInt("Escolha: ") - 1);
                    }
                    break;
            }

        } while (receita == null);
        return receita;
    }

    public void atualizarIngredientes(Receita receita) {
        InterfaceTextual menu = new InterfaceTextual("Atualizar Ingredientes", "Mostrar Ingredientes",
                "Adicionar Ingrediente",
                "Remover Ingrediente", "Voltar");
        Gerenciador gerenciador = new Gerenciador();

        int opcaoSelecionada;
        do {
            opcaoSelecionada = menu.listarOpcoes();
            switch (opcaoSelecionada) {
                case 1:
                    listarIngredientes(gerenciador.getReceitaById(receita.getId()).getListaIngredientes());
                    break;
                case 2:
                    Ingrediente ingrediente = criarIngrediente();
                    gerenciador.getReceitaById(receita.getId()).addIngrediente(ingrediente);
                    break;
                case 3:
                    escrever("Deletar Ingrediente");
                    listarIngredientes(gerenciador.getReceitaById(receita.getId()).getListaIngredientes());
                    gerenciador.getReceitaById(receita.getId()).removeIngrediente(esperarRespostaInt("Escolha: ") - 1);
                    break;
            }
            if (opcaoSelecionada != menu.getOpcoes().size())
                esperarResposta();
            else if (gerenciador.getReceitaById(receita.getId()).getListaIngredientes().isEmpty())
                escrever("Deve haver pelo menos um ingrediente, adicione para continuar.");
        } while (opcaoSelecionada != menu.getOpcoes().size()
                || gerenciador.getReceitaById(receita.getId()).getListaIngredientes().isEmpty());
    }

    public void atualizarReceita(Receita receita) {
        InterfaceTextual menu = new InterfaceTextual("Atualizar Receita", "Atualizar título da receita",
                "Atualizar tipo da receita", "Atualizar ingredientes da receita",
                "Atualizar modo de preparo da receita", "Voltar");
        Gerenciador gerenciador = new Gerenciador();

        int opcaoSelecionada;
        do {
            opcaoSelecionada = menu.listarOpcoes();
            switch (opcaoSelecionada) {
                case 1:
                    String nome = esperarRespostaString("Digite o novo título da receita: ");
                    gerenciador.updateTitulo(receita.getId(), nome);
                    break;
                case 2:
                    TipoReceita tipo = escolherTipoReceita();
                    gerenciador.updateTipo(receita.getId(), tipo);
                    break;
                case 3:
                    atualizarIngredientes(receita);
                    break;
                case 4:
                    String modoDePreparo = esperarRespostaTexto(
                            "Escreva o modo de preparo, quando finalizar\npule linha e digite \":q\":\n");
                    gerenciador.updateModoDePreparo(receita.getId(), modoDePreparo);
                    break;
            }
            if (opcaoSelecionada != menu.getOpcoes().size())
                esperarResposta();
        } while (opcaoSelecionada != menu.getOpcoes().size());
    }

    public void interagirReceita(Receita receita) {
        InterfaceTextual menu = new InterfaceTextual(receita.getTitulo(), "Mostrar receita",
                "Atualizar receita", "Deletar receita", "Voltar");
        Gerenciador gerenciador = new Gerenciador();
        int opcaoSelecionada;
        do {
            opcaoSelecionada = menu.listarOpcoes();
            switch (opcaoSelecionada) {
                case 1:
                    mostrarReceita(receita);
                    break;
                case 2:
                    atualizarReceita(receita);
                    break;
                case 3:
                    escrever("Deletar Receita");
                    if (esperarRespostaInt("Tem certeza? (1 - Sim | 2 - Não)\n") == 1) {
                        gerenciador.delete(receita.getId());
                        escrever("Receita de %s deletada com sucesso.", receita.getTitulo());
                        opcaoSelecionada = menu.getOpcoes().size();
                    } else
                        escrever("Deleção cancelada.");
                    break;
            }
            if (opcaoSelecionada != menu.getOpcoes().size())
                esperarResposta();
        } while (opcaoSelecionada != menu.getOpcoes().size());
    }
}
