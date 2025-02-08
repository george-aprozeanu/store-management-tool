package com.aprozeanu.smt.model.store;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Store {

    @Id
    private String name;

    @ManyToOne
    private Market market;

    @OneToMany(mappedBy = "store")
    private Set<StoreSection> storeSections;

    public Store() {
    }

    public Set<StoreSection> getStoreSections() {
        return storeSections;
    }

    public void setStoreSections(Set<StoreSection> storeSections) {
        this.storeSections = storeSections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
