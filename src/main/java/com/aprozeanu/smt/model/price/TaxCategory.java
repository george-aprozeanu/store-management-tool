package com.aprozeanu.smt.model.price;

import jakarta.persistence.*;

@Entity
public record TaxCategory(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                          @Column String name, @Column Integer percent) {
    private TaxCategory() {
        this(null, null, null);
    }
}
