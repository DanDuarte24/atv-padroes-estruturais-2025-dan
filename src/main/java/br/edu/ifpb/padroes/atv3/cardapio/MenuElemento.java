package br.edu.ifpb.padroes.atv3.cardapio;

public interface MenuElemento {
    String getNome();
    String getDescricao();
    double getPreco();
    /**
     * Imprime o elemento com indentação. Ex.: "" (raiz), "  " (filho), etc.
     */
    void imprimir(String indent);
}
