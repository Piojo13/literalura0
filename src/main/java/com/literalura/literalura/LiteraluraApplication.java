package com.literalura.literalura;

import com.literalura.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/// // Esto de abajo hay que quitarlo al final del TP
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final LibroService libroService;

    public LiteraluraApplication(LibroService libroService) {
        this.libroService = libroService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }
/// //Reemplazar lo de abajo por >>principal.mostrarMenu();<<<
    @Override
    public void run(String... args) {

        libroService.buscarYGuardarLibroPorTitulo("Don Quijote");
    }
}
