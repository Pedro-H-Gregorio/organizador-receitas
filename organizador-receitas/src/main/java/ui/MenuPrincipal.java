package ui;

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
        return TipoUnidadeMedida.values()[new InterfaceTextual("Tipo da Unidade de Medida",
                TipoUnidadeMedida.getTiposUnidadeMedidaInString()).listarOpcoes() - 1];
    }

    public TipoReceita escolherTipoReceita() {
        return TipoReceita.values()[new InterfaceTextual("Tipo da Receita", TipoReceita.getTiposReceitasInString())
                .listarOpcoes() - 1];
    }

    public void criarIngrediente(int receitaId) {
        Gerenciador gerenciador = new Gerenciador();
        String nome = esperarRespostaString("Digite o nome do ingrediente: ");
        TipoUnidadeMedida unidadeMedida = escolherUnidadeMedida();
        float quantidade = esperarRespostaFloat(String.format("Digite a quantidade de %s: ", nome));

        gerenciador.addIngrediente(receitaId, nome, unidadeMedida, quantidade);
    }

    public void criarListaDeIngredientes() {
        InterfaceTextual criadorIngredientes = new InterfaceTextual("Ingredientes", "Novo Ingrediente", "Sair");
        Gerenciador gerenciador = new Gerenciador();
        int opcaoSelecionada;

        do {
            opcaoSelecionada = criadorIngredientes.listarOpcoes();
            if (opcaoSelecionada == 1)
                criarIngrediente(gerenciador.getLastReceita().getId());
            else if (gerenciador.getLastReceita().getListaIngredientes().isEmpty())
                escrever("Adicione pelo menos um ingrediente.");
        } while (opcaoSelecionada != 2 || gerenciador.getLastReceita().getListaIngredientes().isEmpty());
    }

    public int buscarReceita() {
        Gerenciador gerenciador = new Gerenciador();
        int receitaId = -1;
        InterfaceTextual buscadorReceita = new InterfaceTextual("Buscar Receita", "Buscar receita pelo nome",
                "Buscar receita pelo tipo");
        do {
            switch (buscadorReceita.listarOpcoes()) {
                case 1:
                    String nome = esperarRespostaString("Digite o nome da receita: ");
                    if (gerenciador.readReceitas(nome).isEmpty())
                        escrever("Não existe nenhuma receita com este nome.");
                    else {
                        listarReceitas(nome);
                        receitaId = gerenciador.readReceitas(nome).get(esperarRespostaInt("Escolha: ") - 1).getId();
                    }
                    break;
                case 2:
                    TipoReceita tipo = escolherTipoReceita();
                    if (gerenciador.readReceitas(tipo).isEmpty())
                        escrever("Não existe nenhuma receita com deste tipo ainda.");
                    else {
                        listarReceitas(tipo);
                        receitaId = gerenciador.readReceitas(tipo).get(esperarRespostaInt("Escolha: ") - 1).getId();
                    }
                    break;
            }

        } while (receitaId == -1);
        return receitaId;
    }

    public void criarReceita() {
        Gerenciador gerenciador = new Gerenciador();
        String titulo = esperarRespostaString("Digite o título da receita: ");
        TipoReceita tipo = escolherTipoReceita();

        criarListaDeIngredientes();
        gerenciador.add(titulo, tipo);
        gerenciador.addModoDePreparo(esperarRespostaTexto(
                "Escreva o modo de preparo, quando finalizar\npule linha e digite \":q\":\n"));
    }

    public void atualizarIngredientes(int receitaId) {
        InterfaceTextual menu = new InterfaceTextual("Atualizar Ingredientes", "Mostrar Ingredientes",
                "Adicionar Ingrediente",
                "Remover Ingrediente", "Voltar");
        Gerenciador gerenciador = new Gerenciador();

        int opcaoSelecionada;
        do {
            opcaoSelecionada = menu.listarOpcoes();
            switch (opcaoSelecionada) {
                case 1:
                    listarIngredientes(receitaId);
                    break;
                case 2:
                    criarIngrediente(receitaId);
                    break;
                case 3:
                    escrever("Deletar Ingrediente");
                    listarIngredientes(receitaId);

                    gerenciador.getReceitaById(receitaId).removeIngrediente(esperarRespostaInt("Escolha: ") - 1);
                    break;
            }
            if (opcaoSelecionada != menu.getOpcoes().size())
                esperarResposta();
            else if (gerenciador.getReceitaById(receitaId).getListaIngredientes().isEmpty())
                escrever("Deve haver pelo menos um ingrediente, adicione para continuar.");
        } while (opcaoSelecionada != menu.getOpcoes().size()
                || gerenciador.getReceitaById(receitaId).getListaIngredientes().isEmpty());
    }

    public void atualizarReceita(int receitaId) {
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
                    gerenciador.updateTitulo(receitaId, nome);
                    break;
                case 2:
                    TipoReceita tipo = escolherTipoReceita();
                    gerenciador.updateTipo(receitaId, tipo);
                    break;
                case 3:
                    atualizarIngredientes(receitaId);
                    break;
                case 4:
                    String modoDePreparo = esperarRespostaTexto(
                            "Escreva o modo de preparo, quando finalizar\npule linha e digite \":q\":\n");
                    gerenciador.updateModoDePreparo(receitaId, modoDePreparo);
                    break;
            }
            if (opcaoSelecionada != menu.getOpcoes().size())
                esperarResposta();
        } while (opcaoSelecionada != menu.getOpcoes().size());
    }

    public void interagirReceita(int receitaId) {
        Gerenciador gerenciador = new Gerenciador();
        InterfaceTextual menu = new InterfaceTextual(gerenciador.getReceitaById(receitaId).getTitulo(),
                "Mostrar receita",
                "Atualizar receita", "Deletar receita", "Voltar");
        int opcaoSelecionada;
        do {
            opcaoSelecionada = menu.listarOpcoes();
            switch (opcaoSelecionada) {
                case 1:
                    mostrarReceita(receitaId);
                    break;
                case 2:
                    atualizarReceita(receitaId);
                    break;
                case 3:
                    escrever("Deletar Receita");
                    if (esperarRespostaInt("Tem certeza? (1 - Sim | 2 - Não)\n") == 1) {
                        gerenciador.delete(receitaId);
                        escrever("Receita de %s deletada com sucesso.",
                                gerenciador.getReceitaById(receitaId).getTitulo());
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
