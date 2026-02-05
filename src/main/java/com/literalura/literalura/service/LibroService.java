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
    private final GutendexClient gutendexClient;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.gutendexClient = new GutendexClient();
    }

    // Guarda un libro desde el DTO recibido de la API
    public void guardarLibroDesdeDTO(LibroDTO libroDTO) {

        Libro libro = new Libro(
                libroDTO.getTitulo(),
                libroDTO.getIdiomas().get(0)
        );
        libro.setDescargas(libroDTO.getDownload_count());

        // Tomar solo el primer autor
        AutorDTO autorDTO = libroDTO.getAutores().get(0);

        Autor autor = autorRepository
                .findByNombreIgnoreCase(autorDTO.getNombre())
                .orElseGet(() -> {
                    Autor nuevoAutor = new Autor(
                            autorDTO.getNombre(),
                            autorDTO.getAnioNacimiento(),
                            autorDTO.getAnioFallecimiento()
                    );
                    return autorRepository.save(nuevoAutor);
                });

        libro.setAutores(List.of(autor));
        libroRepository.save(libro);
    }

    // Busca un libro por título en la base de datos o API y lo guarda
    public void buscarYGuardarLibroPorTitulo(String titulo) {

        if (libroRepository.findByTituloIgnoreCase(titulo).isPresent()) {
            System.out.println("El libro ya existe en la base de datos.");
            return;
        }

        LibroDTO libroDTO = gutendexClient.buscarLibroPorTitulo(titulo);

        if (libroDTO == null) {
            System.out.println("No se encontró el libro en la API.");
            return;
        }

        guardarLibroDesdeDTO(libroDTO);
        System.out.println("Libro guardado correctamente.");
    }

    // Lista todos los libros con Título, Idioma y Descargas
    public void listarLibros() {
        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }

        libros.forEach(libro ->
                System.out.println(
                        "Título: " + libro.getTitulo() +
                                " | Idioma: " + libro.getIdioma() +
                                " | Descargas: " + libro.getDescargas()
                )
        );
    }

    // Lista todos los autores con Nombre y años de vida
    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
            return;
        }

        autores.forEach(autor ->
                System.out.println(
                        "Autor: " + autor.getNombre() +
                                " (" + autor.getAnioNacimiento() + " - " +
                                (autor.getAnioFallecimiento() != null ? autor.getAnioFallecimiento() : "Presente") + ")"
                )
        );
    }

    public void mostrarCantidadLibrosPorIdioma(String idioma) {
        long cantidad = libroRepository.countByIdiomaIgnoreCase(idioma);
        System.out.println("Cantidad de libros en idioma '" + idioma + "': " + cantidad);
    }
    public void listarAutoresVivosEnAño(int año) {
        List<Autor> autoresVivos = autorRepository
                .findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanOrAnioFallecimientoIsNull(año, año);

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + año);
            return;
        }

        autoresVivos.forEach(autor ->
                System.out.println(
                        "Autor: " + autor.getNombre() +
                                " (" + autor.getAnioNacimiento() + " - " +
                                (autor.getAnioFallecimiento() != null ? autor.getAnioFallecimiento() : "Presente") + ")"
                )
        );
    }

}
