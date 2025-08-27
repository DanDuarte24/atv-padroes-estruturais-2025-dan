package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.xpto.Song;

public class SongAdapter {

    private final Song song;

    public SongAdapter(Song song) {
        this.song = song;
    }

    public Musica toMusica() {
        Long year = song.year();
        return new Musica(song.id(), song.title(), song.artist(), year, song.genre());
    }
}
