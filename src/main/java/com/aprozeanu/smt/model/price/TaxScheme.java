package com.aprozeanu.smt.model.price;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class TaxScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private Set<TaxCategory> taxCategories;

    public TaxScheme() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TaxCategory> getTaxCategories() {
        return taxCategories;
    }

    public void setTaxCategories(Set<TaxCategory> taxCategories) {
        this.taxCategories = taxCategories;
    }
}
