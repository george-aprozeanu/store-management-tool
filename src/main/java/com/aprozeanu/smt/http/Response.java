package com.aprozeanu.smt.http;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class Response {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    /*
     * Suppression of OptionalUsedAsFieldOrParameterType:
     * The method is intended to process an optional type by its nature, it is not an accident.
     * While it could be written with an nullable T, writing it like this simply makes Optionals pointless.
     * e.g. foundOptional(T t) { return t != null ? Response::ok : ResponseEntity.notFound().build(); }
     */
    public static <T> ResponseEntity<T> foundOptional(Optional<T> optional) {
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
