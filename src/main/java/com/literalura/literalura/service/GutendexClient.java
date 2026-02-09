package com.literalura.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.literalura.dto.GutendexResponseDTO;
import com.literalura.literalura.dto.LibroDTO;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GutendexClient {

    private static final String URL = "https://gutendex.com/books/?search=";

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public LibroDTO buscarLibroPorTitulo(String titulo) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + titulo.replace(" ", "%20")))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            GutendexResponseDTO data =
                    mapper.readValue(response.body(), GutendexResponseDTO.class);

            if (data.getResults().isEmpty()) {
                return null;
            }

            return data.getResults().get(0);

        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
            return null;
        }
    }
}
