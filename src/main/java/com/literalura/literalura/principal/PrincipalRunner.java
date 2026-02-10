package com.literalura.literalura.principal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PrincipalRunner implements CommandLineRunner {

    private final Principal principal;

    public PrincipalRunner(Principal principal) {
        this.principal = principal;
    }

    @Override
    public void run(String... args) {
        principal.mostrarMenu();
    }
}
