package com.aprozeanu.smt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.aprozeanu.smt.model")
public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
