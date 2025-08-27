package br.edu.ifpb.padroes.atv3.cardapio;

public class MainCardapio {
    public static void main(String[] args) {
        // Itens individuais
        Item burger = new Item("Hambúrguer Clássico", "Pão, carne 160g, queijo, alface, tomate", 24.90);
        Item batata = new Item("Batata Frita", "Porção individual", 9.90);
        Item refri = new Item("Refrigerante Lata", "350ml", 6.50);
        Item sorvete = new Item("Sorvete", "1 bola de creme", 7.00);

        // Combos (Composite) — combos podem conter itens OU outros combos
        Combo comboAlmoco = new Combo("Combo Almoço", "Hambúrguer + Batata + Refrigerante", 10.0)
                .adicionar(burger)
                .adicionar(batata)
                .adicionar(refri);

        Combo comboFamilia = new Combo("Combo Família", "2x Combo Almoço + Sobremesa", 15.0)
                .adicionar(comboAlmoco)
                .adicionar(comboAlmoco)
                .adicionar(sorvete);

        // Cardápio raiz
        Cardapio cardapio = new Cardapio()
                .adicionar(burger)
                .adicionar(batata)
                .adicionar(refri)
                .adicionar(sorvete)
                .adicionar(comboAlmoco)
                .adicionar(comboFamilia);

        cardapio.imprimir();
    }
}
