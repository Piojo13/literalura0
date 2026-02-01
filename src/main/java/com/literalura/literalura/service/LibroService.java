package com.literalura.literalura.service;

import com.literalura.literalura.dto.LibroDTO;
import com.literalura.literalura.dto.AutorDTO;
import com.literalura.literalura.model.Autor;
import com.literalura.literalura.model.Libro;
import com.literalura.literalura.repository.AutorRepository;
import com.literalura.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void guardarLibroDesdeDTO(LibroDTO libroDTO) {

        Libro libro = new Libro(
                libroDTO.getTitle(),
                libroDTO.getLanguages().get(0)
        );

        List<Autor> autores = new ArrayList<>();

        for (AutorDTO autorDTO : libroDTO.getAuthors()) {

            Autor autor = new Autor(
                    autorDTO.getName(),
                    autorDTO.getBirth_year(),
                    autorDTO.getDeath_year()
            );

            autorRepository.save(autor);
            autores.add(autor);
        }

        libro.setAutores(autores);
        libroRepository.save(libro);
    }
}
