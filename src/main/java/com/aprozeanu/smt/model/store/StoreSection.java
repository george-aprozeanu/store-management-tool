package com.aprozeanu.smt.model.store;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public record StoreSection(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                           @Column String name,
                           @Column String description,
                           @OneToMany(mappedBy = "section") Set<StoreSectionEntry> storeSectionEntrySet) {
    private StoreSection() {
        this(null, null, null, null);
    }
}
