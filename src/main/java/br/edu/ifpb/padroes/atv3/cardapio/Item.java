package br.edu.ifpb.padroes.atv3.cardapio;

import java.text.NumberFormat;
import java.util.Locale;

public class Item implements MenuElemento {
    private static final NumberFormat CURRENCY =
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private final String nome;
    private final String descricao;
    private final double preco;

    public Item(String nome, String descricao, double preco) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome obrigatório");
        if (preco < 0) throw new IllegalArgumentException("preço não pode ser negativo");
        this.nome = nome;
        this.descricao = descricao == null ? "" : descricao;
        this.preco = preco;
    }

    @Override public String getNome() { return nome; }
    @Override public String getDescricao() { return descricao; }
    @Override public double getPreco() { return preco; }

    @Override
    public void imprimir(String indent) {
        System.out.println(indent + "- " + nome + " (" + CURRENCY.format(preco) + ")" +
                (descricao.isBlank() ? "" : " — " + descricao));
    }
}
