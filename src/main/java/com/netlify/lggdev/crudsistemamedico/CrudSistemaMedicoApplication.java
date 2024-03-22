package com.netlify.lggdev.crudsistemamedico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.logging.Logger;

@SpringBootApplication
public class CrudSistemaMedicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudSistemaMedicoApplication.class, args);
    }
}
