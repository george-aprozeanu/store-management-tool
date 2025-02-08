package com.aprozeanu.smt.model.product;

import jakarta.persistence.*;

@Entity
public record Product(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                      @Column String name,
                      @Column String description) {
}