package com.literalura.literalura.repository;

import com.literalura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloIgnoreCase(String titulo);
    long countByIdiomaIgnoreCase(String idioma);
    List<Libro> findByIdiomaIgnoreCase(String idioma);

}
