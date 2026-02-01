package com.literalura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponseDTO {

    private List<LibroDTO> results;

    public List<LibroDTO> getResults() {
        return results;
    }
}