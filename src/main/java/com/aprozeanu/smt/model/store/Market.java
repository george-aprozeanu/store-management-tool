package com.aprozeanu.smt.model.store;

import com.aprozeanu.smt.model.price.Currency;
import com.aprozeanu.smt.model.price.TaxScheme;
import jakarta.persistence.*;

@Entity
public record Market(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                     @OneToOne Currency currency, @OneToOne TaxScheme taxScheme) {
    private Market() {
        this(null, null, null);
    }
}
