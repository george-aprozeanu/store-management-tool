package com.aprozeanu.smt.model.store;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class StoreSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "storeSection")
    private List<StoreSectionEntry> sectionEntries;

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

    public List<StoreSectionEntry> getSectionEntries() {
        return sectionEntries;
    }

    public void setSectionEntries(List<StoreSectionEntry> sectionEntries) {
        this.sectionEntries = sectionEntries;
    }
}
