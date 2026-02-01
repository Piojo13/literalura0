package com.literalura.literalura;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.literalura.dto.GutendexResponseDTO;
import com.literalura.literalura.dto.LibroDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TestApiGutendex {

    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://gutendex.com/books/?search=don%20quijote"))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        GutendexResponseDTO data =
                mapper.readValue(response.body(), GutendexResponseDTO.class);

        for (LibroDTO libro : data.getResults()) {
            System.out.println("Título: " + libro.getTitle());
            System.out.println("Idioma: " + libro.getLanguages());

            libro.getAuthors().forEach(autor-> {
                System.out.println("Autor: " + autor.getName());
            });

            System.out.println("------------------");
        }
    }
}
