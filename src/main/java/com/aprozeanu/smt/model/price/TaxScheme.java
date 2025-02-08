package com.aprozeanu.smt.model.price;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class TaxScheme {

    @Id
    private String name;

    @ManyToMany
    private Set<TaxCategory> taxCategories;

    public TaxScheme() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TaxCategory> getTaxCategories() {
        return taxCategories;
    }

    public void setTaxCategories(Set<TaxCategory> taxCategories) {
        this.taxCategories = taxCategories;
    }

}
