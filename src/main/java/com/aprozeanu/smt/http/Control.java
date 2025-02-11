package com.aprozeanu.smt.http;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class Control {
    public static <T> ResponseEntity<T> foundOptional(Optional<T> optional) {
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
