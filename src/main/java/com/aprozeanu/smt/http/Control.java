package com.aprozeanu.smt.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class Control {
    public static <T> T foundOptional(Optional<T> optional) {
        return optional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
    }
}
