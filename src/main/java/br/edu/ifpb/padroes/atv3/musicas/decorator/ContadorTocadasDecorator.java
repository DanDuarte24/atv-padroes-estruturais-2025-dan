package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;

import java.util.HashMap;
import java.util.Map;

public class ContadorTocadasDecorator extends TocadorMusicaDecorator {

    private final Map<String, Integer> contador = new HashMap<>();

    public ContadorTocadasDecorator(br.edu.ifpb.padroes.atv3.musicas.servico.TocadorMusica tocador) {
        super(tocador);
    }

    @Override
    protected void executarComportamentoExtra(Musica musica) {
        String key = musica.titulo() + " - " + musica.artista();
        contador.put(key, contador.getOrDefault(key, 0) + 1);
        System.out.println(">> Estatística: '" + key + "' tocada " + contador.get(key) + " vez(es).");
    }

    public Map<String, Integer> getContador() {
        return Map.copyOf(contador);
    }
}
