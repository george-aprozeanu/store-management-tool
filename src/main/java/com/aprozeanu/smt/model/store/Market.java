package com.aprozeanu.smt.model.store;

import com.aprozeanu.smt.model.price.Currency;
import com.aprozeanu.smt.model.price.TaxScheme;
import jakarta.persistence.*;

@Entity
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Currency currency;

    @OneToOne
    private TaxScheme taxScheme;

    public Market() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
