package com.aprozeanu.smt.model.store;

import com.aprozeanu.smt.model.price.Currency;
import com.aprozeanu.smt.model.price.TaxScheme;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Market {

    @Id
    private String name;
    @OneToOne
    private Currency currency;

    @OneToOne
    private TaxScheme taxScheme;

    public Market() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TaxScheme getTaxScheme() {
        return taxScheme;
    }

    public void setTaxScheme(TaxScheme taxScheme) {
        this.taxScheme = taxScheme;
    }
}
