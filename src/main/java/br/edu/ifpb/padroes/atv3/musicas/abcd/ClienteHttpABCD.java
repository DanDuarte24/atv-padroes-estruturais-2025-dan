package br.edu.ifpb.padroes.atv3.musicas.abcd;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ClienteHttpABCD {

    private static final String ARQUIVO = "musicas-stream-abcd.json";

    public List<Musica> listarMusicas() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(ARQUIVO));

            // Verifica se o JSON é um array direto
            if (rootNode.isArray()) {
                return objectMapper.readValue(
                        new File(ARQUIVO),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Musica.class)
                );
            }
            // Se for um objeto, procura por campos comuns que possam conter o array
            else if (rootNode.has("musicas")) {
                return objectMapper.readValue(
                        rootNode.get("musicas").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Musica.class)
                );
            }
            else if (rootNode.has("songs")) {
                return objectMapper.readValue(
                        rootNode.get("songs").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Musica.class)
                );
            }
            else {
                throw new RuntimeException("Formato JSON não reconhecido em " + ARQUIVO + ". Estrutura esperada: array direto ou objeto com campo 'musicas'/'songs'.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo JSON: " + ARQUIVO, e);
        }
    }
}