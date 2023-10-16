package gerenciador;

import classes.Receita;
import enuns.TipoReceita;
import enuns.TipoUnidadeMedida;

public interface IGerenciador {
    void add(String titulo, TipoReceita tipo);
    void add(int quantidade, TipoUnidadeMedida tipoUnidade, String nome);
    void update(); //update receita
    void updade(Receita receita); // update ingrediente
    void readReceita();
    Receita findReceita();
    void deleteReceita(int index);
}
