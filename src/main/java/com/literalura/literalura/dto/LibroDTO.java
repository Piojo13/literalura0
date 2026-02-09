package com.literalura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("authors")
    private List<AutorDTO> autores;

    @JsonAlias("download_count")
    private Integer download_count;

    public String getTitulo() {
        return titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public Integer getDownload_count(){
        return download_count;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Idiomas: " + idiomas + ", Autores: " + autores;
    }
}
