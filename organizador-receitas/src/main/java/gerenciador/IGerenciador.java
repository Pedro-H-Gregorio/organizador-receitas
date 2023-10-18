package gerenciador;

import classes.Receita;
import enuns.TipoReceita;
import enuns.TipoUnidadeMedida;

public interface IGerenciador {
    void add(String titulo, TipoReceita tipo);
    void addIngrediente(int quantidade, TipoUnidadeMedida tipoUnidade, String nome);
    void addModoDePreparo(String modoDePreparo);
    void update(int index); //update receita
    void updade(Receita receita, int index); // update ingrediente
    void readReceita();
    void readReceita(TipoReceita... tipos);
    void readReceita(String titulo);
    Receita getReceita(int index);
    void delete(int index);
    void delete(Receita receita, int index);
}
