package br.edu.ifpb.padroes.atv3.musicas.xpto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ClientHttpXPTO {

    private static final String ARQUIVO = "musicas-stream-xpto.json";

    public List<Song> findAll() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(ARQUIVO));

            if (rootNode.isArray()) {
                return objectMapper.readValue(
                        new File(ARQUIVO),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Song.class)
                );
            }
            else if (rootNode.has("songs")) {
                return objectMapper.readValue(
                        rootNode.get("songs").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Song.class)
                );
            }

            else if (rootNode.has("musics")) {
                return objectMapper.readValue(
                        rootNode.get("musics").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Song.class)
                );
            }


            else if (rootNode.has("musicas")) {
                return objectMapper.readValue(
                        rootNode.get("musicas").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Song.class)
                );
            }
            else {
                throw new RuntimeException("Formato JSON não reconhecido em " + ARQUIVO + ". Estrutura esperada: array direto ou objeto com campo 'songs'/'musicas'.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo JSON: " + ARQUIVO, e);
        }
    }
}