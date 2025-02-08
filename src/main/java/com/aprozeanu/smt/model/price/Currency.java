package com.aprozeanu.smt.model.price;

import jakarta.persistence.*;

@Entity
public record Currency(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                       @Column String symbol, @Column Integer multiplier) {
    private Currency() {
        this(null, null, null);
    }
}
