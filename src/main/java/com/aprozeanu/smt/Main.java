package com.aprozeanu.smt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
