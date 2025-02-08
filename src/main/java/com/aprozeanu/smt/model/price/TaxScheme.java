package com.aprozeanu.smt.model.price;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public record TaxScheme(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                        @OneToMany Set<TaxCategory> taxCategories) {
    private TaxScheme() {
        this(null, null);
    }
}
