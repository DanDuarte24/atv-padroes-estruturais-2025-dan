package br.edu.ifpb.padroes.atv3.musicas.servico;

public class MusicaNaoEncontradaException extends RuntimeException {
    public MusicaNaoEncontradaException() {
        super("Música não encontrada");
    }
}
