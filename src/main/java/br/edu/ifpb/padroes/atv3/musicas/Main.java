package br.edu.ifpb.padroes.atv3.musicas;

import br.edu.ifpb.padroes.atv3.musicas.abcd.ClienteHttpABCD;
import br.edu.ifpb.padroes.atv3.musicas.decorator.ContadorTocadasDecorator;
import br.edu.ifpb.padroes.atv3.musicas.facade.MusicaFacade;
import br.edu.ifpb.padroes.atv3.musicas.proxy.MusicaProxy;
import br.edu.ifpb.padroes.atv3.musicas.servico.TocadorMusica;
import br.edu.ifpb.padroes.atv3.musicas.xpto.ClientHttpXPTO;

public class Main {
    public static void main(String[] args) {
        ClienteHttpABCD abcd = new ClienteHttpABCD();
        ClientHttpXPTO xpto = new ClientHttpXPTO();

        MusicaProxy proxy = new MusicaProxy(abcd, xpto);
        TocadorMusica tocadorBase = new TocadorMusica();
        ContadorTocadasDecorator tocador = new ContadorTocadasDecorator(tocadorBase);

        MusicaFacade facade = new MusicaFacade(proxy, tocador);

        System.out.println("--- Listando todas as músicas ---");
        facade.listarTodas().forEach(m -> System.out.println(m.titulo() + " — " + m.artista()));

        System.out.println("\n--- Buscando por artista 'Caetano' ---");
        facade.buscarPorArtista("Caetano").forEach(m -> facade.tocarMusica(m));

        System.out.println("\n--- Buscando por gênero 'MPB' ---");
        facade.buscarPorGenero("MPB").forEach(m -> facade.tocarMusica(m));
    }
}
