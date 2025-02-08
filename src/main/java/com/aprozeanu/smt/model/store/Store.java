package com.aprozeanu.smt.model.store;

import jakarta.persistence.*;

@Entity
public record Store(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                    @OneToOne Market market) {
    private Store() {
        this(null, null);
    }
}
