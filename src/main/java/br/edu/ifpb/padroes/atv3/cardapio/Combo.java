package br.edu.ifpb.padroes.atv3.cardapio;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Combo implements MenuElemento {
    private static final NumberFormat CURRENCY =
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private final String nome;
    private final String descricao;
    private final List<MenuElemento> elementos = new ArrayList<>();
    private final double descontoPercentual; // 0..100

    public Combo(String nome, String descricao) {
        this(nome, descricao, 0.0);
    }

    public Combo(String nome, String descricao, double descontoPercentual) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome obrigatório");
        if (descontoPercentual < 0 || descontoPercentual > 100)
            throw new IllegalArgumentException("desconto deve estar entre 0 e 100");
        this.nome = nome;
        this.descricao = descricao == null ? "" : descricao;
        this.descontoPercentual = descontoPercentual;
    }

    public Combo adicionar(MenuElemento elemento) {
        if (elemento == null) throw new IllegalArgumentException("elemento nulo");
        elementos.add(elemento);
        return this;
    }

    public Combo remover(MenuElemento elemento) {
        elementos.remove(elemento);
        return this;
    }

    public List<MenuElemento> getElementos() {
        return List.copyOf(elementos);
    }

    @Override public String getNome() { return nome; }
    @Override public String getDescricao() { return descricao; }

    @Override
    public double getPreco() {
        double subtotal = elementos.stream().mapToDouble(MenuElemento::getPreco).sum();
        double fator = 1.0 - (descontoPercentual / 100.0);
        return Math.round(subtotal * fator * 100.0) / 100.0; // duas casas
    }

    @Override
    public void imprimir(String indent) {
        String desc = descricao.isBlank() ? "" : " — " + descricao;
        String descDesc = descontoPercentual > 0 ? " (desconto: " + descontoPercentual + "%)" : "";
        System.out.println(indent + "+ Combo: " + nome + " (" + CURRENCY.format(getPreco()) + ")" + desc + descDesc);
        String childIndent = indent + "  ";
        for (MenuElemento e : elementos) {
            e.imprimir(childIndent);
        }
    }
}
