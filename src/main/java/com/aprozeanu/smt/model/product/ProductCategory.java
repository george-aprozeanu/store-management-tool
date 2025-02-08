package com.aprozeanu.smt.model.product;

import jakarta.persistence.*;

@Entity
public record ProductCategory(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                              @Column String name,
                              @Column String description) {
    private ProductCategory() {
        this(null, null, null);
    }
}
