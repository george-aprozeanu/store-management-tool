package com.aprozeanu.smt.model.store;

import com.aprozeanu.smt.model.price.TaxCategory;
import com.aprozeanu.smt.model.product.Product;
import jakarta.persistence.*;

@Entity
public record StoreSectionEntry(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                                @ManyToOne StoreSection section,
                                @ManyToOne Product product,
                                @ManyToOne TaxCategory taxCategory,
                                Integer price
) {
    private StoreSectionEntry() {
        this(null, null, null, null, null);
    }
}
