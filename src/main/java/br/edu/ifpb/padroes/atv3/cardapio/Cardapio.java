package br.edu.ifpb.padroes.atv3.cardapio;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cardapio {
    private static final NumberFormat CURRENCY =
            NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    private final List<MenuElemento> elementos = new ArrayList<>();

    public Cardapio adicionar(MenuElemento elemento) {
        if (elemento == null) throw new IllegalArgumentException("elemento nulo");
        elementos.add(elemento);
        return this;
    }

    public List<MenuElemento> getElementos() {
        return List.copyOf(elementos);
    }

    public double getPrecoTotal() {
        return Math.round(elementos.stream().mapToDouble(MenuElemento::getPreco).sum() * 100.0) / 100.0;
    }

    public void imprimir() {
        System.out.println("=== Cardápio ===");
        for (MenuElemento e : elementos) {
            e.imprimir("");
        }
        System.out.println("----------------");
        System.out.println("Total: " + CURRENCY.format(getPrecoTotal()));
    }
}
