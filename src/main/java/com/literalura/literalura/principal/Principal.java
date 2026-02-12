package com.literalura.literalura.principal;

import com.literalura.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private final LibroService libroService;
    private Scanner scanner = new Scanner(System.in);

    public Principal(LibroService libroService) {
        this.libroService = libroService;
    }


    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
        
        ========= LITERALURA =========
        1 - Buscar libro por título
        2 - Listar libros registrados
        3 - Listar autores registrados
        4 - Listar autores vivos por año
        5 - Cantidad de libros por idioma
        0 - Salir
        ===============================
        """);


            opcion = scanner.nextInt();
            scanner.nextLine(); // limpia buffer

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> {
                    System.out.println("Ingrese el año para consultar autores vivos:");
                    int año = scanner.nextInt();
                    scanner.nextLine();
                    libroService.listarAutoresVivosEnAño(año);
                }
                case 5 -> {

                    System.out.println("""
            
    Seleccione idioma:
    1 - English (en)
    2 - Español (es)
    """);

                    int idiomaOpcion = scanner.nextInt();
                    scanner.nextLine();

                    String idioma = "";

                    switch (idiomaOpcion) {
                        case 1 -> idioma = "en";
                        case 2 -> idioma = "es";
                        default -> {
                            System.out.println("Idioma inválido");
                            return;
                        }
                    }

                    libroService.listarLibrosPorIdiomaConAutores(idioma);
                }
                case 0 -> System.out.println("Nos vemos pronto");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();

        libroService.buscarYGuardarLibroPorTitulo(titulo);
    }

    private void listarLibros() {
        libroService.listarLibros();
    }

    private void listarAutores() {
        libroService.listarAutores();
    }
}