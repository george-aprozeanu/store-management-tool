package com.aprozeanu.smt.model.product;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public record Product(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                      @Column String name,
                      @Column String description,
                      @ManyToMany()
                      Set<ProductCategory> categories
) {
    private Product() {
        this(null, null, null, null);
    }
}