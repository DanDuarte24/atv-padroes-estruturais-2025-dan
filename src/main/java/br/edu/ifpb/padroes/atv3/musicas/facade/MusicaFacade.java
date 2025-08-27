package br.edu.ifpb.padroes.atv3.musicas.facade;

import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.proxy.MusicaProxy;
import br.edu.ifpb.padroes.atv3.musicas.servico.TocadorMusica;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MusicaFacade {

    private final MusicaProxy proxy;
    private final TocadorMusica tocador;

    public MusicaFacade(MusicaProxy proxy, TocadorMusica tocador) {
        this.proxy = proxy;
        this.tocador = tocador;
    }

    public List<Musica> buscarPorTitulo(String titulo) {
        String t = titulo == null ? "" : titulo.toLowerCase(Locale.ROOT);
        return proxy.listarMusicas().stream()
                .filter(m -> m.titulo().toLowerCase(Locale.ROOT).contains(t))
                .collect(Collectors.toList());
    }

    public List<Musica> buscarPorArtista(String artista) {
        String a = artista == null ? "" : artista.toLowerCase(Locale.ROOT);
        return proxy.listarMusicas().stream()
                .filter(m -> m.artista().toLowerCase(Locale.ROOT).contains(a))
                .collect(Collectors.toList());
    }

    public List<Musica> buscarPorGenero(String genero) {
        String g = genero == null ? "" : genero.toLowerCase(Locale.ROOT);
        return proxy.listarMusicas().stream()
                .filter(m -> m.genero() != null && m.genero().toLowerCase(Locale.ROOT).contains(g))
                .collect(Collectors.toList());
    }

    public void tocarMusica(Musica musica) {
        tocador.tocarMusica(musica);
    }

    public List<Musica> listarTodas() {
        return proxy.listarMusicas();
    }
}
