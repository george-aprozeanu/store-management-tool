package com.aprozeanu.smt.model.store;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class StoreSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "section")
    private Set<StoreSectionEntry> storeSectionEntrySet;

    @ManyToOne
    private Store store;

    public StoreSection() {
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StoreSectionEntry> getStoreSectionEntrySet() {
        return storeSectionEntrySet;
    }

    public void setStoreSectionEntrySet(Set<StoreSectionEntry> storeSectionEntrySet) {
        this.storeSectionEntrySet = storeSectionEntrySet;
    }
}
