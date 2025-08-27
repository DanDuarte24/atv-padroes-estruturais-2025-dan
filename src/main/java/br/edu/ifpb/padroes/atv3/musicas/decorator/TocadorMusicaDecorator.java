package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.servico.TocadorMusica;

public abstract class TocadorMusicaDecorator extends TocadorMusica {
    protected final TocadorMusica tocador;

    public TocadorMusicaDecorator(TocadorMusica tocador) {
        this.tocador = tocador;
    }

    @Override
    public void tocarMusica(Musica musica) {
        tocador.tocarMusica(musica);
        executarComportamentoExtra(musica);
    }

    protected abstract void executarComportamentoExtra(Musica musica);
}
