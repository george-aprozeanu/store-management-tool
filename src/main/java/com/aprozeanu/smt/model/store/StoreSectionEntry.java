package com.aprozeanu.smt.model.store;

import com.aprozeanu.smt.model.price.TaxCategory;
import com.aprozeanu.smt.model.product.Product;
import jakarta.persistence.*;

@Entity
public class StoreSectionEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StoreSection section;

    @ManyToOne
    private Product product;

    @ManyToOne
    private TaxCategory taxCategory;

    private Integer price;

    public StoreSectionEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoreSection getSection() {
        return section;
    }

    public void setSection(StoreSection section) {
        this.section = section;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public TaxCategory getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        this.taxCategory = taxCategory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}