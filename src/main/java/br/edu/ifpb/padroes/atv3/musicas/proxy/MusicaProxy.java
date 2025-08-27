package br.edu.ifpb.padroes.atv3.musicas.proxy;

import br.edu.ifpb.padroes.atv3.musicas.abcd.ClienteHttpABCD;
import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.xpto.ClientHttpXPTO;
import br.edu.ifpb.padroes.atv3.musicas.adapter.SongAdapter;
import br.edu.ifpb.padroes.atv3.musicas.xpto.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicaProxy {

    private List<Musica> cacheMusicas;

    private final ClienteHttpABCD clienteABCD;
    private final ClientHttpXPTO clienteXPTO;

    public MusicaProxy(ClienteHttpABCD abcd, ClientHttpXPTO xpto) {
        this.clienteABCD = abcd;
        this.clienteXPTO = xpto;
    }

    public List<Musica> listarMusicas() {
        if (cacheMusicas == null) {
            cacheMusicas = new ArrayList<>();
            // carregar do ABCD
            List<Musica> abcdList = clienteABCD.listarMusicas();
            if (abcdList != null) cacheMusicas.addAll(abcdList);
            // carregar do XPTO e adaptar
            // A chamada para clienteXPTO.findAll() agora corresponde ao método corrigido.
            List<Song> songs = clienteXPTO.findAll();
            if (songs != null) {
                for (Song s : songs) {
                    cacheMusicas.add(new SongAdapter(s).toMusica());
                }
            }
        }
        return Collections.unmodifiableList(cacheMusicas);
    }

    // opcional: invalidar cache se necessário
    public void invalidarCache() {
        this.cacheMusicas = null;
    }
}
